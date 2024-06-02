<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Liked Users">
    <meta name="author" content="Your Name">
    <link rel="icon" href="img/favicon.ico">

    <title>Liked Users</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/css/style.liked-list.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Liked Users</h1>
    <div class="row">
        <#list users as user>
            <div class="col-12 col-md-6 col-lg-4 user-card">
                <form method="post" action="/liked">
                    <input type="hidden" name="userId" value="${user.id}">
                    <div class="card" onclick="this.parentElement.submit()">
                        <div class="card-img-container">
                            <img class="card-img-top user-img" src="${user.imgUrl}" alt="${user.name}">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${user.name}</h5>
                        </div>
                    </div>
                </form>
            </div>
        </#list>
    </div>
</div>
<!-- Bootstrap core JavaScript -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>