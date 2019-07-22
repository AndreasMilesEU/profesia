from __future__ import absolute_import
import scrapy
from scrapyProfesia.items import vacancyItem


roles = ['java', 'python', 'javascript', 'security-analyst']


class ProfesiaJava(scrapy.Spider):
    name = "profesia_java"
    start_urls = [
        "https://www.profesia.sk/praca/?search_anywhere=" + roles[0]
    ]

    def parse(self, response):
        last_page_number = 50
        if last_page_number < 1:
            return
        else:
            # looping through the pages
            page_urls = [response.url + "&page_num=" + str(pageNumber) for pageNumber in range(last_page_number)]
            for page_url in page_urls:
                yield scrapy.Request(page_url,
                                     callback=self.parse_listing_results_page)

    def parse_listing_results_page(self, response):
        item = vacancyItem()

        titles = response.xpath('//ul[@class="list"]//span[@class="title"]/text()').extract()
        links = response.xpath('//ul[@class="list"]//a[contains(@id,"offer")]/@href').extract()

        for i in range(len(titles)):
            item["title"] = titles[i]
            link = "https://profesia.sk" + links[i]
            item["link"] = link
            item["status"] = "apply"

            yield item


class ProfesiaPython(scrapy.Spider):
    name = "profesia_python"
    start_urls = [
        "https://www.profesia.sk/praca/?search_anywhere=" + roles[1]
    ]

    def parse(self, response):
        last_page_number = 50
        if last_page_number < 1:
            return
        else:
            # looping through the pages
            page_urls = [response.url + "&page_num=" + str(pageNumber) for pageNumber in range(last_page_number)]
            for page_url in page_urls:
                yield scrapy.Request(page_url,
                                     callback=self.parse_listing_results_page)

    def parse_listing_results_page(self, response):
        item = vacancyItem()

        titles = response.xpath('//ul[@class="list"]//span[@class="title"]/text()').extract()
        links = response.xpath('//ul[@class="list"]//a[contains(@id,"offer")]/@href').extract()

        for i in range(len(titles)):
            item["title"] = titles[i]
            link = "https://profesia.sk" + links[i]
            item["link"] = link
            item["status"] = "apply"

            yield item


class ProfesiaJavaScript(scrapy.Spider):
    name = "profesia_java_script"
    start_urls = [
        "https://www.profesia.sk/praca/?search_anywhere=" + roles[2]
    ]

    def parse(self, response):
        last_page_number = 50
        if last_page_number < 1:
            return
        else:
            # looping through the pages
            page_urls = [response.url + "&page_num=" + str(pageNumber) for pageNumber in range(last_page_number)]
            for page_url in page_urls:
                yield scrapy.Request(page_url,
                                     callback=self.parse_listing_results_page)

    def parse_listing_results_page(self, response):
        item = vacancyItem()

        titles = response.xpath('//ul[@class="list"]//span[@class="title"]/text()').extract()
        links = response.xpath('//ul[@class="list"]//a[contains(@id,"offer")]/@href').extract()

        for i in range(len(titles)):
            item["title"] = titles[i]
            link = "https://profesia.sk" + links[i]
            item["link"] = link
            item["status"] = "apply"

            yield item