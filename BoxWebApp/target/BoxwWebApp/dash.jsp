<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Box Content Explorer</title>
  <!-- polyfill.io only loads the polyfills your browser needs -->
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=es6,Intl"></script>
    <!-- Alternatively, use polyfill hosted on the Box CDN
    <script src="https://cdn01.boxcdn.net/polyfills/core-js/2.5.3/core.min.js"></script>
    -->

    <!-- Latest version of the explorer css for your locale -->
    <link
      rel="stylesheet"
      href="https://cdn01.boxcdn.net/platform/elements/11.0.2/en-US/explorer.css"
    />
</head>
<body>
 <div class="container" style="height:600px"></div>
    <!-- Latest version of the explorer js for your locale -->
    <script src="https://cdn01.boxcdn.net/platform/elements/11.0.2/en-US/explorer.js"></script>
    
    
    <script>
      var folderId = "${rootFolder.getID()}";
      var accessToken = "${accessToken}";
      var contentExplorer = new Box.ContentExplorer();
      contentExplorer.show(folderId, accessToken, {
        container: ".container"
      });
    </script>
</body>
</html>


  
