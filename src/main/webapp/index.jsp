<!DOCTYPE html>
<html>
<head>
    <title>Form to create a new resource</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
        $(function(){


            /*OBJECT*/
            function Todo(summary, description,id) {
                this.id= id;
                this.summary= summary;
                this.description = description;
                this.toJsonString = function () {
                    var rtn={};
                    if(this.id)rtn["id"]=this.id;
                    if(this.summary)rtn["summary"]=this.summary;
                    if(this.description)rtn["description"]=this.description;
                    return JSON.stringify(rtn);
                    /*
                    //var rtn = JSON.stringify(this)
                    var rtn = this;
                    return rtn;
                    */
                };

            }

            var baseUrl= "http://localhost:8080/simple-jersey-spring/rest/todos/";


            /**  todoServiceConfig list of object defining actions
             *  this key is the value of the $('[name="action"]') select in the form
             *  object : {
             *      "type" : http method of the service request
             *      ,"url" : url of the service to request
             *      ,"callback" : success callback
             *
             *
             */
            var todoServiceConfig={
                "create" : {
                    "type" : "POST"
                    ,"url" : baseUrl
                    ,"callback" : logCallback
                }
                ,"read" : {
                    "type" : "GET"
                    ,"url" : baseUrl
                    ,"idParam" : true
                    ,"callback" : logCallback
                }
                ,"readAll" : {
                    "type" : "GET"
                    ,"noObj" : true
                    ,"url" : baseUrl
                    ,"callback" : logCallback
                }
                ,"update" : {
                    "type" : "PUT"
                    ,"url" : baseUrl + "update"
                    ,"callback" : logCallback
                }
                ,"delete" : {
                    "type" : "DELETE"
                    ,"url" : baseUrl
                    ,"idParam" : true
                    ,"noObj" : true
                    ,"callback" : logCallback
                }
            };


            $("#todoForm").submit(function(e){
                e.preventDefault();
                doRequest(
                        new Todo(
                                $('[name="summary"]').val(),
                                $('[name="description"]').val(),
                                $('[name="id"]').val()
                        )
                        ,todoServiceConfig[$('[name="action"]').val()]
                );
                return false;
            });

            function doRequest(obj,requestConfig){
                var param = "";
                if(requestConfig.idParam){
                    if(!obj.id || obj.id ==="")return alert("need an id");
                    param =obj.id;
                }
                jQuery.ajax({
                    type: requestConfig.type,
                    url: requestConfig.url + param,
                    data:  (!requestConfig.noObj ? obj.toJsonString() : undefined),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data, status, jqXHR) {
                        requestConfig.callback(data, status, jqXHR);
                    },
                    error: function (jqXHR, status) {
                        console.warn("request to web service failed");
                        logCallback({}, status, jqXHR);
                    }

                });
            }

            function logCallback(data, status, jqXHR){
                console.log("data",data);
                console.log("status",status);
                console.log("jqXHR",jqXHR);
            }
        })
    </script>
</head>
<body>
<a href="rest/todos">List</a>
<form id="todoForm" action="rest/todos" method="POST">
    <select name="action">
        <option value="create">Create</option>
        <option value="readAll">Get all</option>
        <option value="read">Get one</option>
        <option value="update">update</option>
        <option value="delete">delete</option>
    </select>
    <label for="id">id</label>
    <input name="id" />
    <br/>
    <label for="summary">Summary</label>
    <input name="summary" />
    <br/>
    Description:
    <TEXTAREA NAME="description" COLS=40 ROWS=6></TEXTAREA>
    <br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>