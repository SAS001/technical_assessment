# technical_assessment
This repository is used to store automation scripts related to an technical assessment

# Technology/Tools used
Java, Selenium WebDriver, Maven, Chrome browser, Eclipse IDE, Spread sheets, property files, Data driven, TestNG, Extent reports

# Automated test scenarios
Operations - Bank Manager
1. Add new customers
2. Verify added new customers - First name, Last name, Post code
3. Delete selected customers

Operations - Customer
1. Perform credit/debit transactions
2. Calculate and verify current and customer hompage available balance

# Project Architecture
This project has been implemented considering data driven approach with page factory model. Spread sheets and property files have been used to
feed data to automation scripts/methods. This approach has increased the re-usability and easy to maintain. TestNG is the execution framework and 
has followed the standard annotations. Seperate framework core with re-usable methods have been implemented to handle the data, execution parameters, report generation etc.
Apart from the TestNG report, meaningful execution report from extent report will be created after each execution.

# Spread sheets location : "./datasheets/"+workbookName+".xlsx"
# Property file location: "./src/test/resources/config.properties"
# Execution report location: "./reports"
# Automation scripts location: "./src/test/java/com/regression"

# How to execute
1.Seperate xml file (testng.xml) has been designed to execute from single click. 
2.location: "./testng.xml"
3.After importing to IDE let the project to download maven dependencies using pom.xml (commond: mvn clean install)
4.finally need to right click on the testng.xml and run via testng.
5.If need automation scripts can be executed seperately

#Execution evidence
Please note that execution evidence have been commmited in the reports folder (./reports). It contains three reports
1. Bank Manager operations report : 20230412_035536_Report
2. Customer operations report: 20230412_035557_Report
3. Bulk execution report: 20230412_035617_Report

#---Thank you

