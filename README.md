# BinaryHakaton
Hakaton 

Ovako za testiranje aplikacije ukoliko se aplikacija testira preko android studia moze se jednostavno pokrenuti na emulatoru ili koristeci 
usb debugging/wifi debugging. Uglavnom ovdje je i link: https://www.mediafire.com/file/a0e3h0qvpuoldzz/app-debug.apk/file sa kojeg se moze skinuti buildan apk pa moze se i tako testirati.

BITNO:
Radi manjka vremena te okolonosti koje su bile prisutne kao post i umor, uspjeli smo odradit dosta featura od toga:
Dodavanje artikla (Moguce dodati samo Automobil te odjecu radi naveddenog razloga ali kada su te dvije kategorije dodane mogu se
			i ostale dodati vrlo lako)
Pregled artikla, sva njegova svojstva, razlicit pregled kod automobila i kod odjece
Kod automobila omoguceno mijenjanje fotografija te pregled svih ostalih karakteristika, razlog sto nema mjenjanja fotki na odjeci jeste sto smo mislili da nema potrebe
Dodavanje artikala u listu favorita, odakle iste moguce skloniti odatle
Menu koji pokazuje sve dodane artikle sa naseg profila.
Tu je i shoping kart menu koji nismo stigli do kraja zavrsiti ali ima funkcionalnost kod odjece da se moze dodati u korpu te sve kupiti
			Button za skdanje iz korpe na zalost ne radi
Opcija u gornjem lijevom uglu na home page-u radi kao search ali na osnovu naslova te input mora biti isti kao i naslov da bi radilo
			nismo imali vremena da optimizujemo
Opcija u gornjem desnom uglu jeste menu koji pa skoro i nikako nismo zavrsili od toga radi customer support i log out,
			customer supp radi na nacin slanja mejlova preko gmaila ili neke drugog mail provajdera

Nismo stigli da napravimo da prilikom dodavanja ili nekih menua koji zahtjevaju unos da striktno trazi unos kako bi button radio tako da
prilikom testiranja treba paziti na inpute da budu kako treba inace se moze desiti neki veci Bug ili stovise crash

Prilikom dodavanja fotografija u menuu za kreiranje artikla potrebno je sacekati malo nekih par sekundi prije nego se klikne button za kreiranje artikla
kako bi se fotografije mogle upload u bazu kako treba, tu se mogao dodati neki progress bar ali nije na zalost.
