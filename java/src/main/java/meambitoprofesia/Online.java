package meambitoprofesia;

import meambitoprofesia.Applications;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.DBCursor;
import org.mongojack.WriteResult;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static meambitoprofesia.App.getUserDetails;


public class Online {

    public static void main(String[] args) throws IOException {

        // get UserDetails and Domain specific details cast to a class
        UserDetails userDetails = getUserDetails();

        // DB Connection variables
        String host = userDetails.getHost();
        Integer port = userDetails.getPort();
        String dbName = userDetails.getDbName();
        String collName = userDetails.getCollName();

        // WebDriver - login process
        Domain domain = App.getDomain(new File("Profesia.json"));
        WebDriver driver = new FirefoxDriver();

        // logon
        driver.get(domain.getLoginUrl());
        driverSetValueByName(driver, domain.getEmail(), userDetails.getEmail());
        driverSetValueByName(driver, domain.getPassword(), userDetails.getPassword());
        driverSetValueByName(driver, domain.getLoginButton(), "click");

        // DB
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port));
        DB db = mongoClient.getDB(dbName);
        DBCollection collection = db.getCollection(collName);

        // Cast MongoDB document to a class (for the sake of demo)
        JacksonDBCollection<Applications, String> coll = JacksonDBCollection.wrap(collection,
                Applications.class, String.class);

        // Retrieve all vacancies NOT applied for
        DBCursor<Applications> cursor = coll.find(DBQuery.is("status", "apply"));


        for (Applications application : cursor){
            String status = application.getStatus();
            if(status.contains("applied") || status.contains("custom")) {
                continue;
            }
            //driver.get("file:///home/user/Desktop/profesia/eset.html");
            driver.get(application.getLink());

            // currently WebDriver only applicable to profesia.sk "easy-apply" forms which is app. 70% of vacancies
            try {
                // checking for WebElement alert for having applied in the past (profesia db generated)
                try {
                    // if yes, update the vacancy -> db
                    String text = driver.findElement(By.cssSelector(".alert-dismissable")).getText();
                    if (text.contains("Na túto pracovnú ponuku ste reagovali")) {
                        dbSetStatus(coll, application, "applied");
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // checking for WebElement with the "apply" tag, sometimes the id is appended with "-custom"
                try {
                    WebElement hrefElement = driver.findElement(By.id("offer-reaction"));
                    String href = hrefElement.getAttribute("href");
                    driver.get(href);
                } catch (Exception e) {
                    WebElement hrefElement = driver.findElement(By.id("offer-reaction-custom"));
                    String href = hrefElement.getAttribute("href");
                    driver.get(href);
                }

                driverSetValueByName(driver, "firstname", userDetails.getName());
                driverSetValueByName(driver, "surname", userDetails.getSurname());
                driverSetValueByName(driver, "from_e_mail", userDetails.getEmail());
                driverSetValueByName(driver, "phone", userDetails.getMobile());
                driverSetValueByName(driver, "add_file[9][0]", "click");
                driverSetValueByName(driver, "add_file[24][0]", "click");

                // GDPR req. sometimes there are 2 agreement requirements, (i.e. company && agency?)
                try{
                    driverSetValueByName(driver, "agreement_processing_personal_data_in_reaction", "click");
                } catch (Exception c) {
                    System.out.println("Element not present!");
                }


                driverSetValueByName(driver, "agreement_company", "click");
                driverSetValueByName(driver, domain.getLoginButton(), "click");
                // once applied, update DB status
                dbSetStatus(coll, application, "applied");

            } catch (Exception d){
                dbSetStatus(coll, application, "custom");
                continue;
            }

/*        // SEARCHING FOR VACANCIES VIA WEBDRIVER (Bad idea!)
        driver.get(domain.getUrl());
        // fillout search terms
        for (Iterator < Tag > iterator = userDetails.getTags().iterator(); iterator.hasNext();) {
            Tag tag = (Tag) iterator.next();
            setValueByClass(driver, domain.getSearch(), tag.getName());
        }
        setValueById(driver, domain.getSearchButton(), "click");*/
        driver.close();
        }
    }

    public static void dbSetStatus(JacksonDBCollection coll, Applications application, String status) {
        application.setStatus(status);
        coll.updateById(application.getId(), application);
        System.out.println(application.getStatus());
    }

    public static void driverSetValueByName(WebDriver driver, String name, String value) {
        WebElement element;
        element = driver.findElement(By.name(name));
        if (value != "click") {
            element.clear();
            element.sendKeys(value);
        } else {
            element.click();
        }
    }

    public static void driverSetValueByClass(WebDriver driver, String name, String value) {
        WebElement element;
        element = driver.findElement(By.className(name));
        if (value != "click") {
            element.sendKeys(value);
            element.sendKeys(Keys.ENTER);
        } else {
            element.click();
        }
    }

    public static void driverSetValueById(WebDriver driver, String name, String value) {
        WebElement element;
        element = driver.findElement(By.id(name));
        if (value != "click") {
            element.clear();
            element.sendKeys(value);

        } else {
            element.click();
        }
    }
}
