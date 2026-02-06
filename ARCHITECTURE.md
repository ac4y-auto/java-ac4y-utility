# java-ac4y-utility - Architektúra Dokumentáció

## Áttekintés

Az `ac4y-utility` modul alapvető segédeszközöket biztosít az Ac4y projektekhez. Független külső függőségekkel (Gson, JDOM2, JAXB) rendelkezik, és általános célú utility osztályokat tartalmaz.

**Verzió:** 1.0.0
**Java verzió:** 11
**Szervezet:** ac4y-auto

## Fő Komponensek

### 1. String Kezelés

#### `StringHandler`
Alapvető string műveletek kezelésére szolgál.

**Fő metódusok:**
- `getSimpled(String text)`: Ékezetes karakterek eltávolítása és alfanumerikus karakterekre egyszerűsítés, eredmény nagybetűs
  - Bemenet: bármilyen szöveg
  - Kimenet: nagybetűs, ékezet nélküli, csak betűk és számok
  - Példa: "Árvíztűrő 123!" → "ARVIZTUR123"

- `getExtended(boolean needExtension, String before, String after, String base)`: Feltételes string bővítés
  - Ha needExtension=true: before + base + after
  - Ha needExtension=false: base

- `getEncoded(String input)` / `getEncoded(String input, String codePage)`: URL encoding UTF-8 vagy más kódlappal

- `getLastPart(String string, String separator)`: Elválasztó után az utolsó rész visszaadása
  - Példa: getLastPart("a/b/c", "/") → "c"

- `concatSmart(String first, String second, String separator)`: Intelligens összefűzés
  - Ha first==null: csak second-ot ad vissza
  - Különben: first + separator + second

#### `Ac4yStringHandler`
Azonos funkcionalitás mint a StringHandler, de kifejezetten magyar ékezetes karakterekkel (áíúőűöüóé → aiuououoe).

### 2. GUID Kezelés

#### `GUIDHandler`
UUID generálásra szolgál.

**Metódus:**
- `getGUID()`: Random UUID string generálása (java.util.UUID.randomUUID())

**Használat:**
```java
GUIDHandler guidHandler = new GUIDHandler();
String uniqueId = guidHandler.getGUID(); // pl.: "550e8400-e29b-41d4-a716-446655440000"
```

### 3. Dátum Kezelés

#### `DateHandler`
Dátum műveletek és string konverziók.

**Formátumok:**
- DATEFORMAT: "yyyy-MM-dd"
- TIMEFORMAT: "yyyy-MM-dd HH:mm:ss"

**Metódusok:**
- `getShiftedDate(Date date, int shift)`: Dátum eltolása napokban
  - shift lehet negatív is (múltbeli dátum)

- `getDateFromString(String date)`: String → Date konverzió (DATEFORMAT)
- `getTimeFromString(String time)`: String → Date konverzió (TIMEFORMAT)
- `getStringFromDate(Date date)`: Date → String konverzió (DATEFORMAT)
- `getStringFromTime(Date date)`: Date → String konverzió (TIMEFORMAT)

**Kivételek:** ParseException dobható a parse műveleteknél

### 4. XML Kezelés

#### `XMLHandler`
DOM alapú XML műveletek.

**Fő funkciók:**
- `getCoded(String text)` / `getDecoded(String text)`: URL encoding/decoding UTF-8-cal
- `getAttributeValue(Node node, String name)`: XML node attribútum értékének lekérése
- `getDecodedAttributeValue(Node node, String name)`: Dekódolt attribútum érték
- `documentFromXMLString(String xmlString)`: String → Document
- `documentFromXMLFile(String fileName)`: Fájl → Document
- `getXMLStringFromDocument(Document doc)`: Document → String

**Technológia:** org.w3c.dom.Document, javax.xml.parsers.DocumentBuilder

#### `JDOMXMLHander`
JDOM2 alapú XML műveletek (file-ban van, de nincs részletezve az olvasási kimenetben).

#### `XSLHandler`
XSL transzformációk kezelésére (file-ban van, de nincs részletezve).

### 5. JSON Kezelés

#### `GsonCap`
Google Gson könyvtár wrapper JSON műveletek

hez.

**Metódusok:**
- `getGson()`: Gson instance létrehozása (serializeNulls beállítással)
- `getObjectAsJson(Object object)`: Object → JSON string
- `getFromJson(String json, Class class)`: JSON string → Object

**Konfigurációk:**
- Null értékek szerializálása engedélyezve
- Pretty printing kikommentezve (egysoros JSON)

**Használat:**
```java
GsonCap gson = new GsonCap();
String json = gson.getObjectAsJson(myObject);
MyClass obj = (MyClass) gson.getFromJson(json, MyClass.class);
```

### 6. JAXB Kezelés (XML-Object Mapping)

#### `JaxbCap`
JAXB (Java Architecture for XML Binding) műveletek XML és Object közötti konverzióhoz.

**Marshaller létrehozás (Object → XML):**
- `getMarshaller(String packageName)`: Package név alapján
- `getMarshaller(Class class)`: Class alapján
- Formatted output engedélyezve

**Unmarshaller létrehozás (XML → Object):**
- `getUnMarshaller(String packageName)`: Package név alapján
- `getUnMarshaller(Class class)`: Class alapján

**Konverziós metódusok:**
- `getObjectAsXmlString(String packageName, Object object)`: Object → XML string
- `getObjectAsXmlString(Class class, Object object)`: Object → XML string
- `getObjectFromXmlString(String packageName, String xml)`: XML string → Object
- `getObjectFromXmlString(Class class, String xml)`: XML string → Object

**Kivételek:** JAXBException dobható

**Használat:**
```java
JaxbCap jaxb = new JaxbCap();
String xml = jaxb.getObjectAsXmlString(MyClass.class, myObject);
MyClass obj = (MyClass) jaxb.getObjectFromXmlString(MyClass.class, xml);
```

### 7. Fájl Kezelés

#### `SEQFileHandler`
Szekvenciális fájl kezelés (file-ban van, részletek nem olvashatók).

### 8. Környezet Kezelés

#### `Ac4yEnvironmentHandler`
Környezeti változók és környezet-specifikus konfiguráció (file-ban van, részletek nem olvashatók).

## Függőségek

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>

<dependency>
    <groupId>org.jdom</groupId>
    <artifactId>jdom2</artifactId>
    <version>2.0.6.1</version>
</dependency>

<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>2.3.3</version>
</dependency>

<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.9</version>
</dependency>
```

## Felhasználási Mintázatok

### 1. Egyszerű String Tisztítás
```java
StringHandler sh = new StringHandler();
String clean = sh.getSimpled("Ékezetes Szöveg 123!");
// Result: "EKEZETES SZOVEG 123" → "EKEZETESSZOVEG123"
```

### 2. Dátum Műveletek
```java
DateHandler dh = new DateHandler();
Date today = new Date();
Date tomorrow = dh.getShiftedDate(today, 1);
Date yesterday = dh.getShiftedDate(today, -1);
String dateStr = dh.getStringFromDate(today); // "2025-01-15"
```

### 3. XML Parsing
```java
XMLHandler xh = new XMLHandler();
Document doc = xh.documentFromXMLFile("config.xml");
String xml = xh.getXMLStringFromDocument(doc);
```

### 4. JSON Műveletek
```java
GsonCap gson = new GsonCap();
MyData data = new MyData();
String json = gson.getObjectAsJson(data);
MyData restored = (MyData) gson.getFromJson(json, MyData.class);
```

### 5. JAXB XML-Object Binding
```java
JaxbCap jaxb = new JaxbCap();
MyXmlClass obj = new MyXmlClass();
String xml = jaxb.getObjectAsXmlString(MyXmlClass.class, obj);
MyXmlClass restored = (MyXmlClass) jaxb.getObjectFromXmlString(MyXmlClass.class, xml);
```

## AI Agent Használati Útmutató

### Gyors Döntési Fa

**Kérdés:** Milyen adattípussal dolgozol?

1. **String** → `StringHandler` vagy `Ac4yStringHandler`
   - Ékezet eltávolítás? → `getSimpled()`
   - URL encoding? → `getEncoded()`
   - Utolsó rész kivágás? → `getLastPart()`

2. **Dátum** → `DateHandler`
   - String ↔ Date konverzió? → `getDateFromString()` / `getStringFromDate()`
   - Dátum eltolás? → `getShiftedDate()`

3. **XML** → `XMLHandler` vagy `JaxbCap`
   - DOM műveletek? → `XMLHandler`
   - Object ↔ XML? → `JaxbCap`

4. **JSON** → `GsonCap`
   - Object ↔ JSON? → `getObjectAsJson()` / `getFromJson()`

5. **Egyedi azonosító** → `GUIDHandler`
   - UUID kell? → `getGUID()`

### Token-hatékony Tudás

**Mit tartalmaz:** String, Dátum, GUID, XML, JSON utility-k
**Mit NEM tartalmaz:** Database, HTTP, Process, Context kezelés
**Függőségek:** Gson, JDOM2, JAXB
**Független:** Igen, nincs ac4y-base függőség

## Build és Telepítés

```bash
# Build
mvn clean install

# Test
mvn test

# Deploy to GitHub Packages
mvn deploy
```

**GitHub Packages:**
```xml
<dependency>
    <groupId>ac4y</groupId>
    <artifactId>ac4y-utility</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Verzió Történet

- **1.0.0**: Első kiadás, kiemelt java-ac4y-base modulból
