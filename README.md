By entering a country's name the application will retrieve the code from an API:

http://host:port/api/country/code/by/name?cpuntryname=the_country_name

Replace host and port with the host and port of where the API SpringBoot application is running and replace the  request Parameter: 

"the_country_name"

With one of the country names mensioned in the list at the bottom of this instruction:

in Case there are more tha one code for a country, the application will then return the largest one as the latest.
The API to read codes is implemented in the SpringBoot application:
SpringBootTutorial
The countries currently included in this application are:

Afghanistan,

Iran,

USA,

UK
