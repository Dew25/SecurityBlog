<%-- 
    Document   : uploadFile
    Created on : 25.03.2017, 16:01:34
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Загрузить файл!</h1>
       <form method="POST" action="upload" enctype="multipart/form-data" >
            Выберите файл в вашей файловой системе:
            <input type="file" name="file" id="file" /> <br/>
           
            </br>
            <input type="submit" value="Загрузить" name="upload" id="upload" />
        </form>
    </body>
</html>
