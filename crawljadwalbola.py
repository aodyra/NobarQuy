import requests
from lxml import html
from datetime import datetime

class JadwalBola(object):
    def __init__(self):
        self.date_now = datetime.now()

    # date merupakan objek datetime.now()
    def getJadwal(self, date):
        day = date.day
        month = date.month 
        year = date.year
        print(day)
        print(month)
        print(year)
        link_schedule = "http://www.goal.com/id-ID/fixtures/{}-{}-{}?ICID=RE_CAL_2".format(year,month,day)
        r = requests.get(link_schedule)
        tree = html.fromstring(r.content)
        schedule = tree.xpath('//table[@class="match-table "]//td[@class="team"]//span/text()')
        return schedule


if __name__ == "__main__":
    jadwal = JadwalBola()
    print(jadwal.getJadwal(jadwal.date_now))
