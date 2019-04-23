Spring boot application to search jobs using JQuery, Ajax to call REST API.

1. This application uses Spring Boot to build a REST API which internally calls Open Public API (https://github.com/workforce-data-initiative/skills-api/wiki/API-Overview) to get a skills data.

2. UI provides a way to filter jobs based on skills e.g. filter with keywords like "software", "analyst", "designer", etc.

3. The result is displayed in a table with 4 columns: uuid, suggestion, normalized_job_title, parent_uuid (This is default data returned by public API). By default all entries are sorted using uuid.

4. User can sort result data by any column name using given sort key selection dropdown. 

5. How to Run Application :

5.1. Clone this repository
5.2. Build using Maven
5.3. Launch as Springboot App
5.4. Open browser with link : http://localhost:8080/
