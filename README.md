
* Group name : #44-Spyder Scraper

|  Member | Task | Task |

|----|----|----|

| vganesh | Basic 5 | Basic 6 |

| tbhanushali | Basic 1 | Basic 4 |

| ibatra | Basic 2 | Advanced 3 |

 ## Issue
There is an issue with the "Last Search" button. It is disabled upon initalisation and then re-enabled. The enabled visual state is not restored. The button looks greyed out but it works as if it is enabled.

Upon researching the issue, we found that it is a CSS bug. 
https://bugs.openjdk.java.net/browse/JDK-8106815
