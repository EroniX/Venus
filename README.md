****************************************
*** Tanulmányi rendszer fejlesztése ****
****************************************

Funkcionális követelmények:
- Képzés kiválasztása, majd a képzésre meghirdetett kurzusok listázása (kurzuskód, kurzusnév, oktató, időpont) kurzuskód szerinti sorrendben
- Egy oldalon legfeljebb 20 kurzus listázódik, az oldalak között lapozhatunk
- A lista szűrhető kurzusnév(részlet)re, valamint oktató név(részlet)re
- A hallgató bejelentkezhet (felhasználónév és jelszó megadásával), amelyet követően már csak az általa eddig felvett kurzusok listázódnak
- Külön jelöli az oldal a már teljesített kurzusokat, ahol az érdemjegy is látható
- A lista a korábbiak mellett teljesítettség szerint is szűrhető
- Egy kurzus akkor teljesített, ha legalább elégséges az eredmény
- Emellett a hallgatónak lehetősége van kurzust felvenni, illetve kijelentkezni
- Kurzusfelvétel esetén a teljes kínálatból választhatja ki a kurzust
- Csak olyan kurzusra lehet jelentkezni, amely még nincs teljesítve
- A rendszer csak a versenyjelentkezést támogatja, ezért csak akkor jelentkezhet a kurzusra, ha még van hely
- A jelentkezést követően, amíg nincs teljesítve a kurzus, a hallgató lejelentkezhet
- A kurzuskód automatikusan generálódik a tárgykód és a tárgyon belüli sorszám alapján (<tárgykód>/<sorszám> formában), 
  a név és az oktató is automatikusan hozzárendelődik, csupán a maximális létszámot és a kezdőidőpontot (hét napja, óra, perc) kell megadni
- Ha a hallgató elégtelent kap, akkor természetesen lehet újból érdemjegyet rögzíteni, egyébként nem

Nem funkcionális követelmények:
- Backend l,megvalósításához Java Spring Boot-ot használunk
- Frontend megvalósításához HTML/CSS-t, jQuery-t, Javascript-et és AngularJS 2-t alkalmazunk
- Adatbázisnak MySQL-t, eléréséhez a MySQL JDBC Drivert
- Alkalmazzuk az MVC, Repository és Service patterneket

Szakterületi tartalomjegyzék:
- Tanulmányi rendszer: Iskolai számonkérések és vizsgák elektronikus rögzítésére kifejlesztett rendszer
- Tárgy: Képzés melyet a hallgató elvégez
- Kurzus: Képzés időpontja, melyre a hallgató bejár
- Jegybeírás: Jegy rögzítése a tárgyhoz 
- Vizsga: Tárgyon tanított ismeretek visszaellenőrzése

Szerepkörök:
- STUDENT: Tanuló
- TEACHER: Tanár
 