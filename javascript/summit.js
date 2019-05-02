var serverAddress = "http://127.0.0.1:880";

function evento() {
    if (document.getElementById('evento').style.display == "none") {
        $("#formEvento")[0].reset();
        document.getElementsByClassName("btnAcao")[0].style.display = "none";
        for (i = 5; i < $("#formEvento")[0].length; i++)
            $("#formEvento")[0][i].disabled = true;
        document.getElementById('evento').style.display = "";
        document.getElementById('cliente').style.display = "none";
    }
}

function cliente() {
    if (document.getElementById('cliente').style.display == "none") {
        $("#formCliente")[0].reset();
        document.getElementsByClassName("btnAcao")[1].style.display = "none";
        for (i = 5; i < $("#formCliente")[0].length; i++)
            $("#formCliente")[0][i].disabled = true;
        document.getElementById('evento').style.display = "none";
        document.getElementById('cliente').style.display = "";
    }
}

function habilitaFormEvento(status, funcao) {
    let form = document.querySelector("#formEvento");
    let btnAcao = document.getElementsByClassName("btnAcao")[0];
    if (status == true) {
        btnAcao.style.display = "";
        btnAcao.setAttribute("onclick", "executaEvento('"+funcao+"')");
        if (funcao == "create") {
            btnAcao.textContent = "Cadastrar";
            btnAcao.setAttribute("formaction", serverAddress+"/cadastrarEvento");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                if(form[i].style.display == "none") {
                    jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = ""
                    form[i].style.display = "";
                }
            }
        }
        else if (funcao == "read") {
            btnAcao.textContent = "Consultar";
            btnAcao.setAttribute("formaction", serverAddress+"/consultarEvento");
            form[5].value = "";
            form[5].disabled = false;
            for (i = 6; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = true;
                jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = "none"
                form[i].style.display = "none";
            }
        }
        else if (funcao == "update") {
            btnAcao.textContent = "Atualizar";
            btnAcao.setAttribute("formaction", serverAddress+"/atualizarEvento");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                if(form[i].style.display == "none") {
                    jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = ""
                    form[i].style.display = "";
                }
            }
        }
        else if (funcao == "delete") {
            btnAcao.textContent = "Excluir";
            btnAcao.setAttribute("formaction", serverAddress+"/excluirEvento");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                if(form[i].style.display == "none") {
                    jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = ""
                    form[i].style.display = "";
                }
            }
        }
    }
}

function habilitaFormCliente(status, funcao) {
    let form = document.querySelector("#formCliente");
    let btnAcao = document.getElementsByClassName("btnAcao")[1];
    if (status == true) {
        btnAcao.style.display = "";
        btnAcao.setAttribute("onclick", "executaCliente('"+funcao+"')");
        if (funcao == "create") {
            btnAcao.textContent = "Cadastrar";
            btnAcao.setAttribute("formaction", serverAddress+"/cadastrarCliente");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                if(form[i].style.display == "none") {
                    jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = ""
                    form[i].style.display = "";
                }
            }
        }
        else if (funcao == "read") {
            btnAcao.textContent = "Consultar";
            btnAcao.setAttribute("formaction", serverAddress+"/consultarCliente");
            for (i = 5; i < 7; i++) {
                form[i].value = "";
                form[i].disabled = false;
            }
            for (i = 7; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = true;
                jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = "none"
                form[i].style.display = "none";
            }
        }
        else if (funcao == "update") {
            btnAcao.textContent = "Atualizar";
            btnAcao.setAttribute("formaction", serverAddress+"/atualizarCliente");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                if(form[i].style.display == "none") {
                    jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = ""
                    form[i].style.display = "";
                }
            }
        }
        else if (funcao == "delete") {
            btnAcao.textContent = "Excluir";
            btnAcao.setAttribute("formaction", serverAddress+"/excluirCliente");
            for (i = 5; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                if(form[i].style.display == "none") {
                    jQuery("label[for="+form[i].getAttribute("id")+"]")[0].style.display = ""
                    form[i].style.display = "";
                }
            }
        }
    }
}

function executaEvento(funcao) {
    alert(funcao);
}

function executaCliente(funcao) {
    alert(funcao);
}