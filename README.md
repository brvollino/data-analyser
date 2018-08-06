# data-analyser

This program is a simple data processor and analyzer for *.dat formats in a CSV like format.

## Input
Here goes an example file of the application input, which may contain 3 types of registry.

```
001ç1234567891234çDiegoç50000
001ç3245678865434çRenatoç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato
```

Salesman:
001çCPFçNameçSalary

Customer:
002çCNPJçNameçBusiness Area

Sale:
003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

The input .dat files for processing must must be placed in '{home.dir}/data/in/'.

## Output

The problem's description was is not very clear about what should be output by the program.

At some extent, the description hints that all files should be processed and the summary should be created over all the data, like in here:
"After processing all files inside the input default directory, the system must create a flat file inside the
default output directory"

Otherwise another portion of text sound like there should be created one summary output file for each input file, like in here:
"The filename must follow this pattern, {flat_file_name}.done.dat.
The output file contents should summarize the following data:
- Amount of clients in the input file
- Amount of salesman in the input file"

I chose the first option, so my application takes process all the data files in the directory and creates a summarized view for all the records.

## Implementation

The application was developed with the following technologies:

- Spring boot:
For dependency injection and sugar coating the underlying technology, like JPA and Camel auto configuration.

- Camel:
Camel is a framework that provides an ecosystem of APIs and modules to implement a variety of integration pattern.
It was used to ease the processing of the data files and to help a little in the separation of infrastructural code and business code.

- H2
The in-memory database was used to store the data extracted from the processed files. Here are some reasons why I took this approoach:
  - Can be embedded in the application.
  - Make data analysis faster, given that only new files dropped in the input folder must be processed.
  - Provides flexibility to implement new analytic metrics without the overhead of reprocessing all the data files.

- Gradle:
For the project's dependency management and building.

- Flyway:
As the version control system of the database.

- JUnit, Mockito, Hamcrest:
For testing.

