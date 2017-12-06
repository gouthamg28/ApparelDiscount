# ApparelDiscount
Discounts on apparel

HOW TO RUN
==========
This application can be run using below mentioned steps:
1. Download executable jar file discountapp-0.0.1-SNAPSHOT-jar-with-dependencies.jar
2. Download "discount app input" folder having sample input files.
3. category.txt has category wise discounts.
4. brand.txt has brandwise discounts.
5. Install java 1.8 version.
6. Set JAVA_HOME environment variable like "C:\Program Files\Java\jdk1.8.0_05"
7. Run the application using below command from command prompt where jar file is placed, assuming "discount app input" folder is placed in C:\ drive.
   java -jar discountapp-0.0.1-SNAPSHOT-jar-with-dependencies.jar "C:\discount app input\category.txt" "C:\discount app input\brand.txt"
5. Application parses discount details from input files & prints it on console.
6. It will ask for number of items in inventory, enter a number.
7. Then enter inventory details in the format "1, Arrow,Shirts,800"
8. It will ask for number of customer input choices, so enter a number.
9. It will ask for each choice combination, so enter something like 1,2,3
10. Then it prints the price after applying all discounts as per the criteria.

DESIGN DETAILS
==============
1. As data comes from text files & command prompts added an interface IScanner.
2. There are multiple implementation exists like BrandDiscountScanner, CategoryDiscountScanner, InventoryScanner to scan corresponding data.
3. Applied factory pattern in ScannerFactory which provides appropriate scanner to main application whenever it is required.
4. As categories has parent, child hierarchial relationship introduced an interface ICategory where each category instance will hold information about itself & its parent. Exposed setter API for discount as it has to be updated if its one of its parent in hierarchy has higer value than its own discount.
5. Provided a Logger file & all prints happens from it, which gives centralized control to enable/disable the logs.
6. DataRepository class maintains all the processed data like brandwise discounts, category wise discounts, inventory details etc. In future if application grows & if it requires to have a database we can enhance this class to communicate with DB.
7. Application can be enhanced to have delimeters in input files be configurable.
8. Further enhancement can be done to move all validation to a separate module.

