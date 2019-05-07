var serverAddress = "http://127.0.0.1:880";

function evento() {
    if (document.getElementById('evento').style.display == "none") {
        $("#formEvento")[0].reset();
        document.getElementsByClassName("btnAcao")[0].style.display = "none";
        for (i = 5; i < $("#formEvento")[0].length; i++) {
            if (i < $("#formEvento")[0].length - 1) {
                $("#formEvento")[0][i].disabled = true;
                $("#formEvento")[0][i].style.display = "";
                jQuery("label[for=" + $("#formEvento")[0][i].getAttribute("id") + "]")[0].style.display = "";
            }
            else {
                $("#formEvento")[0][i].style.display = "none";
                jQuery("label[for=" + $("#formEvento")[0][i].getAttribute("id") + "]")[0].style.display = "none";
            }
        }
        document.getElementById('evento').style.display = "";
        document.getElementById('cliente').style.display = "none";
    }
}

function cliente() {
    if (document.getElementById('cliente').style.display == "none") {
        $("#formCliente")[0].reset();
        document.getElementsByClassName("btnAcao")[1].style.display = "none";
        for (i = 5; i < $("#formCliente")[0].length; i++) {
            if (i < $("#formCliente")[0].length - 1) {
                $("#formCliente")[0][i].disabled = true;
                $("#formCliente")[0][i].style.display = "";
                jQuery("label[for=" + $("#formCliente")[0][i].getAttribute("id") + "]")[0].style.display = "";
            }
            else {
                $("#formCliente")[0][i].style.display = "none";
                jQuery("label[for=" + $("#formCliente")[0][i].getAttribute("id") + "]")[0].style.display = "none";
            }
        }
        document.getElementById('evento').style.display = "none";
        document.getElementById('cliente').style.display = "";
    }
}

function habilitaFormEvento(status, funcao) {
    let form = document.querySelector("#formEvento");
    let btnAcao = document.getElementsByClassName("btnAcao")[0];
    if (status == true) {
        btnAcao.style.display = "";
        btnAcao.setAttribute("onclick", "executaEvento('" + funcao + "')");
        // btnAcao.setAttribute("formmethod", "POST");
        if (funcao == "create") {
            btnAcao.textContent = "Cadastrar";
            btnAcao.className = "btnAcao btn btn-primary";
            //btnAcao.setAttribute("formaction", serverAddress + "/cadastrarEvento");
            for (i = 5; i < form.length; i++) {
                if (i < form.length - 1) {
                    form[i].value = "";
                    form[i].disabled = false;
                    form[i].style.display = "";
                    jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
                }
                else {
                    form[i].style.display = "none";
                    jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
                }
            }
        }
        else if (funcao == "read") {
            btnAcao.textContent = "Consultar";
            btnAcao.className = "btnAcao btn btn-info";
            // btnAcao.setAttribute("formaction", serverAddress + "/consultarEvento");
            // btnAcao.setAttribute("formmethod", "GET");
            form[5].value = "";
            form[5].disabled = false;
            for (i = 6; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = true;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
                form[i].style.display = "none";
            }
        }
        else if (funcao == "update") {
            btnAcao.textContent = "Atualizar";
            btnAcao.className = "btnAcao btn btn-primary";
            // btnAcao.setAttribute("formaction", serverAddress + "/atualizarEvento");
            // btnAcao.setAttribute("formmethod", "GET");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
                form[i].style.display = "";
            }
        }
        else if (funcao == "delete") {
            btnAcao.textContent = "Excluir";
            btnAcao.className = "btnAcao btn btn-danger";
            // btnAcao.setAttribute("formaction", serverAddress + "/excluirEvento");
            // btnAcao.setAttribute("formmethod", "GET");
            form[5].value = "";
            form[5].disabled = false;
            for (i = 6; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = true;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none"
                form[i].style.display = "none";
            }
        }
    }
}

function habilitaFormCliente(status, funcao) {
    let form = document.querySelector("#formCliente");
    let btnAcao = document.getElementsByClassName("btnAcao")[1];
    if (status == true) {
        btnAcao.style.display = "";
        btnAcao.setAttribute("onclick", "executaCliente('" + funcao + "')");
        if (funcao == "create") {
            btnAcao.textContent = "Cadastrar";
            // btnAcao.setAttribute("formaction", serverAddress + "/cadastrarCliente");
            // btnAcao.setAttribute("formmethod", "POST");
            for (i = 5; i < form.length; i++) {
                if (i < form.length - 1) {
                    form[i].value = "";
                    form[i].disabled = false;
                    form[i].style.display = "";
                    jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
                }
                else {
                    form[i].style.display = "none";
                    jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
                }
            }
        }
        else if (funcao == "read") {
            btnAcao.textContent = "Consultar";
            // btnAcao.setAttribute("formaction", serverAddress + "/consultarCliente");
            // btnAcao.setAttribute("formmethod", "GET");
            for (i = 5; i < 7; i++) {
                form[i].value = "";
                form[i].disabled = false;
            }
            for (i = 7; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = true;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
                form[i].style.display = "none";
            }
        }
        else if (funcao == "update") {
            btnAcao.textContent = "Atualizar";
            // btnAcao.setAttribute("formaction", serverAddress + "/atualizarCliente");
            // btnAcao.setAttribute("formmethod", "GET");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
                form[i].style.display = "";
            }
        }
        else if (funcao == "delete") {
            btnAcao.textContent = "Excluir";
            // btnAcao.setAttribute("formaction", serverAddress + "/excluirCliente");
            // btnAcao.setAttribute("formmethod", "GET");
            for (i = 5; i < 7; i++) {
                form[i].value = "";
                form[i].disabled = false;
            }
            for (i = 7; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = true;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
                form[i].style.display = "none";
            }
        }
    }
}

function executaEvento(funcao) {
    let form = document.querySelector("#formEvento");
    if (funcao == "update") {
        for (i = 6; i < form.length; i++) {
            form[i].required = false;
        }
    }
    if (!form.checkValidity())
        alert("Preencha o formulário corretamente!");
    else {
        var xmlhttp = new XMLHttpRequest();
        console.log("antes do open e send");
        xmlhttp.open("POST", serverAddress + "/cadastrarEvento", true);
        xmlhttp.timeout = 1000;
        xmlhttp.ontimeout = function (e) {
            console.log("// XMLHttpRequest timed out.");
        }
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send($('#formEvento').serialize());
        console.log($('#formEvento').serialize()); 
        console.log("apos o open e send");

        xmlhttp.onreadystatechange = function (e) {
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status >= 200) {
                    console.log("requisicao OK. " + xmlhttp.response);
                    sessionStorage.setItem("dadosXMLHTTP", xmlhttp.response)
                    //location = "";
                } else {
                    console.error("erro na requisicao. //" + xmlhttp.statusText);
                }
            }
        }

        console.log("apos o onreadystatechange");

            xmlhttp.onerror = function (e) {
            console.error(xmlhttp.statusText);
            }
        }
    } 

function executaCliente(funcao) {
    let form = document.querySelector("#formCliente");
    if (funcao == "update")
        for (i = 6; i < form.length; i++) {
            form[i].required = false;
        }
    if (!form.checkValidity())
        alert("Preencha o formulário corretamente!");
}