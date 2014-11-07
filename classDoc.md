# Class Document
### Package.File
## Class ExcelConvert

Modifier and Type |Method and Description                                               
---|---
void|**writingExcelFile**(ArrayList<String[]> arrList, int rownum)
 |Fills in the sheet with strings in the arraylist from the row number
void|**createExcelFile**(String filename)
 |Creates new Exceel file with filename
ArrayList\<double[]\> |**readExcelFileNum**(String filepath, String sheetname, int colNum)
 |Reads #th column values with number
ArrayList\<String[]\>|**readExcelFileStr**(String filepath, String sheetname, int colNum)
 |Reads #th column values with string


### Package.Web.APIs.Foursquare
##Class FoursquareAPI

Modifier and Type |Method and Description
---|---
ArrayList\<String[]\> |**venues_search**(double radius, double lat, double lng)
 |API-Venues-Search

##Class FsURL

Modifier and Type|Method and Description
---|---
void|**makeURL\_venues_search**(double radius, double lat, double lng)
 | just in FoursquareAPI class

##Class FsToken

Modifier and Type | Method and Description
---|---
void|**setToken**(String filepath, String sheetname)
 | just in FsURL class
ArryaList\<String[]\>|**tokenReady**(String filepath, String sheetname)
 | Reads token components from an excel sheet, just in setToken function
String[]|**tokenIterator**(ArrayList\<String[]\> tokenList)
 | Select one token among tokens


###Package Web.Parser
##Class JsonReader

Modifier and Type | Method and Description
---|---
JSONbject | **readJsonFromUrl**(String url)
 | Just in FoursquareAPI

