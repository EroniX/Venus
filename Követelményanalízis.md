## 1.1. Célkitűzés

### 1.1.1. Projektindító dokumentum

**Ez egy egyszerű, de nagyszerű tanulmányi rendszer oldala.**

Regisztráció után a felhasználó **beléphet a rendszerbe**, és utána fel tudja venni a számára megfelelő **szakot**.

Egy felhasználó **több szakot is felvehet**, ha úgy érzi, hogy párhuzamosan több szakot is képes teljesíteni

A felhasználó a **szakok listáját szűrheti**, hogy csak a felvett szakokat lássa, ha annyira sok szakot venne fel.

A felhasználó ha felvette az adott szakot, **fel tudja venni az adott szakhoz tartozó tárgyak kurzusait**, azokat , amelyek számára a legmegfelelőbb.

A felhasználó az **adott kurzuson belül** megtekintheti a **kurzus adatait**.

A kurzus adatainál a felhasználó megtaláltja a **hallgatók listáját**, a kurzus **limitét**, **jegyét**, illetve **egyéb megjegyzést**.

A felhasználó a tárgyak listájánál is tud **szűrni** a sajátjaira

A **tanárok** képesek  tárgyakat **létrehozni**, illetve a tárgyakhoz **kurzusokat meghírdetni**.

Az **tanárok** ezen felül **jegyet írhatnak be**a kurzus hallgatóinak.

### 1.1.2. Funkcionális elvárások

* Regisztráció
* Szakok listázása
* Tárgyak listázása
* Szemeszterek listázása
* Kurzus hallgatóinak listázása
* Felhasználó saját tárgyainak listázása 
* Felhasználó saját szakjainak listázása
* Felhasználó saját kurzusainak listázása
* Felhasználó saját szemesztereinek listázása


#### Hallgatók által elérhető funkciók

* Saját kurzusok böngészése
* Adott szakhoz tartozó kurzusra jelentkezés
* Hallgató számára megfelelő szakra való feljelentkezés
* Tárgyak fevétele, hogy összegyűjtse a 27 kreditet évente 
* Egy kurzushoz tartozhat:
  - Id
  - kurzus neve
  - kurzus kódja
  - tanár
  - kurzus kapacitása
  - kurzusra járó hallgatók listája
 
* A hallgató képes lejelentkezni az adott kurzusról, ha a **tanár** nem felel meg neki.
 

#### Tanárok által elérhető funkciók

* Tárgy létrehozása
* Adott tárgyhoz való kurzusok létrehozása
* A kurzushoz tartozó hallgató értékelése jegy formájában


### 1.1.3. Nem funkcionális elvárások

* Felhasználóbarát, egyszerű, letisztult felület
* Keresési eredmények gyors megjelenítése
* Jelszavas azonosítás, jelszavak biztonságos tárolása


## 1.2. Szakterületi fogalomjegyzék

* **szemeszter**: egyetemen egy félév intervalluma
* **kurzus**: egyetemen egy adott tárgyhoz tartozó csoport
* **szak**: egyetemen a különböző tantervek elnevezése


## 1.3. Use-case modell

### 1.3.1. Szerepkörök

* **hallgató**: hallgató, kurzusokat vehet fel, amelyeken részt kell vennie, és teljesíteni kell azokat
* **tanár**: regisztrált és bejelentkezett látogató, aki saját galériáit menedzselheti, és kedvelhet képeket


## 1.3.2. Use-case modell

![Use-case diagram](images/use_case.png)

### 1.3.3. Kurzusra való jelentkezés menete

![Kurzusra való jelentkezés menete](images/folyamat.png)
