# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html
from __future__ import absolute_import
import scrapy


class vacancyItem(scrapy.Item):
    # coordinates = scrapy.Field()
    title = scrapy.Field()
    link = scrapy.Field()
    status = scrapy.Field()
