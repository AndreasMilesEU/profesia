#!/usr/bin/env bash

source /home/user/IdeaProjects/scrapyProfesia/venv/bin/activate
scrapy crawl profesia_java
scrapy crawl profesia_python
scrapy crawl profesia_java_script

