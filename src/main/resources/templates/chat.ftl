<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/css/style.css">

</head>
<body>

<div class="container">

    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">${chatPartner.name}</h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>

            <div class="chat-content flex-grow-1 overflow-auto">

                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">

                    <ul class="p-0">
                        <#if messages?size == 0>
                        <li class="no-messages-li text-center text-muted width-full">
                            <p class="no-messages">There are no messages. Start conversation.</p>
                        </li>
                        <#else>
                        <#list messages as row>
                        <#if row.chatOwner.id != chatPartner.id>
                        <li class="send-msg float-right mb-2">
                            <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                ${row.content}
                            </p>
                        </li>
                        <#else>
                        <li class="receive-msg float-left mb-2">
                            <div class="sender-img">
                                <img src="${row.chatOwner.imgUrl}" class="float-left">
                            </div>
                            <div class="receive-msg-desc float-left ml-2">
                                <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                    ${row.content}
                                </p>
                                <span class="receive-msg-time">${row.chatOwner.name} ${row.date?string("dd.MM.yyyy HH:mm")}</span>
                            </div>
                        </li>
                    </#if>
                </#list>
            </#if>
            </ul>


        </div>
        <div class="col-md-12 p-2 msg-box border border-primary">
            <form class="chat-form" method="post">
                <input class='chat-input' type="text" name="content" placeholder="Send message">
                <button class="chat-button" type="submit">Send</button>
            </form>

        </div>
    </div>

</div>
</div>

</div>

</body>
</html>