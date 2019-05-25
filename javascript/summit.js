var serverAddress = "http://127.0.0.1:880";
var dadosXMLHTTP;
var clientes;

function evento() {
    if (document.getElementById('evento').style.display == "none") {
        $("#formEvento")[0].reset();
        document.getElementsByClassName("btnAcao")[0].style.display = "none";
        document.getElementsByClassName("btnUpdate")[0].style.display = "none";
        document.getElementsByClassName("btnDelete")[0].style.display = "none";
        document.getElementsByClassName("btnOperacao")[0].style.display = "none";
        for (i = 3; i < $("#formEvento")[0].length; i++) {
            if (i != 4) {
                $("#formEvento")[0][i].disabled = true;
                $("#formEvento")[0][i].style.display = "";
                jQuery("label[for=" + $("#formEvento")[0][i].getAttribute("id") + "]")[0].style.display = "";
            }
            else {
                $("#formEvento")[0][i].style.display = "none";
                jQuery("label[for=" + $("#formEvento")[0][i].getAttribute("id") + "]")[0].style.display = "none";
                $(".colNovoNome").addClass("d-none");
                $(".colNome").removeClass("d-none");
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
        document.getElementsByClassName("btnUpdate")[1].style.display = "none";
        document.getElementsByClassName("btnDelete")[1].style.display = "none";
        document.getElementsByClassName("btnOperacao")[0].style.display = "none";
        for (i = 3; i < $("#formCliente")[0].length; i++) {
            if (i != 4) {
                $("#formCliente")[0][i].disabled = true;
                $("#formCliente")[0][i].style.display = "";
                jQuery("label[for=" + $("#formCliente")[0][i].getAttribute("id") + "]")[0].style.display = "";
            }
            else {
                $("#formCliente")[0][i].style.display = "none";
                jQuery("label[for=" + $("#formCliente")[0][i].getAttribute("id") + "]")[0].style.display = "none";
                $(".colNovoNome").addClass("d-none");
                $(".colNome").removeClass("d-none");

            }
        }
        document.getElementById('evento').style.display = "none";
        document.getElementById('cliente').style.display = "";
    }
}

function habilitaForm(status, funcao, modulo) {
    let form = document.querySelector(`#form${modulo}`);
    let indiceForm;
    if (modulo == "Evento") {
        indiceForm = 0;
    }
    else if (modulo == "Cliente") {
        indiceForm = 1;
    }
    let btnAcao = document.getElementsByClassName("btnAcao")[indiceForm];
    if (status == true) {
        btnAcao.style.display = "";
        document.getElementsByClassName("btnUpdate")[indiceForm].style.display = "none";
        document.getElementsByClassName("btnDelete")[indiceForm].style.display = "none";
        document.getElementsByClassName("btnOperacao")[0].style.display = "none";
        btnAcao.setAttribute("onclick", `executaXML("${funcao}","${modulo}")`);
        form[3].readOnly = false;
        if (funcao == "create") {

            btnAcao.textContent = "Cadastrar";
            btnAcao.className = "btnAcao btn btn-primary";
            //btnAcao.setAttribute("formaction", serverAddress + "/cadastrarEvento");
            for (i = 3; i < form.length; i++) {
                if (i != 4) {
                    form[i].value = "";
                    form[i].disabled = false;
                    form[i].style.display = "";
                    jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
                }
                else {
                    form[i].style.display = "none";
                    jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
                    $(".colNovoNome").addClass("d-none");
                    $(".colNome").removeClass("d-none");
                }
            }
        }
        else if (funcao == "read") {
            btnAcao.textContent = "Consultar";
            btnAcao.className = "btnAcao btn btn-info";
            // btnAcao.setAttribute("formaction", serverAddress + "/consultarEvento");
            // btnAcao.setAttribute("formmethod", "GET");
            form[3].value = "";
            form[3].disabled = false;
            for (i = 3; i < form.length; i++) {
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
            for (i = 3; i < form.length; i++) {
                form[i].value = "";
                form[i].disabled = false;
                jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
                form[i].style.display = "";
            }
            $(".colNovoNome").removeClass("d-none");
            $(".colNome").addClass("d-none");

        }
        // else if (funcao == "delete") {
        //     btnAcao.textContent = "Excluir";
        //     btnAcao.className = "btnAcao btn btn-danger";
        //     // btnAcao.setAttribute("formaction", serverAddress + "/excluirEvento");
        //     // btnAcao.setAttribute("formmethod", "GET");
        //     form[3].value = "";
        //     form[3].disabled = false;
        //     for (i = 4; i < form.length; i++) {
        //         form[i].value = "";
        //         form[i].disabled = true;
        //         jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "none";
        //         form[i].style.display = "none";
        //     }
        // }
    }
}

function executaXML(funcao, modulo) {
    let form = document.querySelector(`#form${modulo}`);
    let path;
    if (funcao == "create")
        path = serverAddress + `/cadastrar${modulo}`;
    else if (funcao == "read")
        path = serverAddress + `/consultar${modulo}`;
    else if (funcao == "inscrever")
        path = serverAddress + `/adicionar${modulo}`;
    else if (funcao == "update" || funcao == "delete") {
        if (funcao == "update")
            path = serverAddress + `/atualizar${modulo}`;
        if (funcao == "delete")
            path = serverAddress + `/excluir${modulo}`;
        for (i = 6; i < form.length; i++) {
            form[i].required = false;
        }
    }
    if (form.reportValidity()) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", path, true);
        xmlhttp.timeout = 1000;
        xmlhttp.ontimeout = function (e) {
            console.log("// XMLHttpRequest timed out.");
        }
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        $(".colNome").removeClass("d-none");
        xmlhttp.send($(`#form${modulo}`).serialize());
        console.log("Conteudo do Form: \n" + $(`#form${modulo}`).serialize());
        $(".colNome").addClass("d-none");

        xmlhttp.onreadystatechange = function (e) {
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status >= 200) {
                    console.log("requisicao OK. \n" + xmlhttp.response);
                    // sessionStorage.setItem("dadosXMLHTTP", xmlhttp.response);
                    dadosXMLHTTP = JSON.parse(xmlhttp.response);
                    if (funcao == "read")
                        mostrarPainel(modulo, dadosXMLHTTP);
                    else if (funcao == "create" || funcao == "update" || funcao == "delete")
                        alerta(funcao, modulo);
                }
                else {
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

function operacoesEventos(modulo) {
    let form = document.querySelector(`#form${modulo}`);
    let path;
    if (modulo == "Inscricao") {
        path = serverAddress + `/adicionar${modulo}`;
        dadosXMLHTTP["cpf"] = $('#inputGroupSelect01').val();
        dadosXMLHTTP[$('#campoAdulto').attr("name")] = $('#campoAdulto').val();
        dadosXMLHTTP[$('#campoCrianca').attr("name")] = $('#campoCrianca').val();
    }
    else if (modulo == "Cliente")
        path = serverAddress + `/consultar${modulo}`;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", path, true);
    xmlhttp.timeout = 3000;
    xmlhttp.ontimeout = function (e) {
        console.log("Apos timeout.");
    }
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    if (modulo == "Cliente")
        xmlhttp.send($(`#form${modulo}`).serialize());
    if (modulo == "Inscricao")
        xmlhttp.send($.param(dadosXMLHTTP));
    console.log("Conteudo do Form: \n" + $(`#form${modulo}`).serialize());

    xmlhttp.onreadystatechange = function (e) {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status >= 200) {
                console.log("requisicao OK. \n" + xmlhttp.response);
                clientes = JSON.parse(xmlhttp.response);
                preencheOperacoes(JSON.parse(xmlhttp.response));
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

function consultaRegistro(modulo, indice) {
    $("#myModal").modal('toggle');
    let form = document.querySelector(`#form${modulo}`);
    let indiceForm;
    if (modulo == "Evento") {
        evento();
        indiceForm = 0;
    }
    else if (modulo == "Cliente") {
        cliente();
        indiceForm = 1;
    }
    form[1].checked = true;
    habilitaForm(true, "update", modulo);
    populate(form, dadosXMLHTTP, indice);
    document.getElementsByClassName("btnUpdate")[indiceForm].style.display = "";
    document.getElementsByClassName("btnDelete")[indiceForm].style.display = "";
    document.getElementsByClassName("btnOperacao")[0].style.display = "";
    document.getElementsByClassName("btnAcao")[indiceForm].style.display = "none";
    // for (i = 5; i < form.length - 1; i++) {
    //     form[i].style.display = "";
    //     form[i].disabled = true;
    //     jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
    // }
}

function alerta(funcao, modulo) {
    $(`#form${modulo}`)[0].reset();
    document.getElementById(modulo.toLowerCase()).style.display = "none";
    if (funcao == "create")
        alert(`${modulo} cadastrado com sucesso!`);
    else if (funcao == "update")
        alert(`${modulo} atualizado com sucesso!`);
    else
        alert(`${modulo} excluído com sucesso!`);
}

function populate(form, json, indice) {
    $.each(json[indice], function (key, value) {
        $('[name=' + key + ']', form).val(value);
    })
    $("#formEventoNovoNome").val(json[indice].nome);
    $("#formClienteNovoCPF").val(json[indice].cpf);
    dadosXMLHTTP = json[indice];
    sessionStorage.setItem("dadosXMLHTTP", JSON.stringify(json[indice]));
}

// function editaRegistro(selecao, modulo) {
//     let form = document.querySelector(`#form${modulo}`);
//     form[selecao].checked = true;
//     if (selecao == 2)
//         habilitaForm(true, "update", modulo);
//     else if (selecao == 3) {
//         habilitaForm(true, "delete", modulo);
//     }
//     populate(form, JSON.parse(sessionStorage.getItem("dadosXMLHTTP")));
//     form[5].readOnly = true;
//     if (selecao == 3) {
//         for (i = 6; i < form.length - 1; i++) {
//             form[i].style.display = "";
//             jQuery("label[for=" + form[i].getAttribute("id") + "]")[0].style.display = "";
//         }
//     }
// }


function mostrarPainel(modulo, dados) {
    $("#modalTitle").html(`${modulo}.`);
    if (dados[0] == "null") {
        $("#modalBody").html("Lista vazia.");
        $("#myModal").modal();
    } else if (modulo == "Evento") {

        $("#modalBody").html(function () {
            texto = "";
            for (i = 0; i < dados.length; i++) {
                texto += `<div><a href='#' onclick='consultaRegistro("${modulo}",${i})'>${dados[i].nome}</a></div>`;
            }
            return texto;
        });
        $("#myModal").modal();

    } else if (modulo == "Cliente") {
        $("#modalBody").html(function () {
            texto = "";
            for (i = 0; i < dados.length; i++) {
                texto += `<div><a href='#' onclick='consultaRegistro("${modulo}",${i})'>${dados[i].nome} - CPF: ${dados[i].cpf}</a></div>`;
            }
            return texto;
        });
        $("#myModal").modal();
    }
}

function preencheOperacoes(l) {
    dadosXMLHTTP = JSON.parse(sessionStorage.getItem("dadosXMLHTTP"));
    $("#nomeEvento").html(`Evento: ${dadosXMLHTTP.nome}`);
    for (let i = 0; i < l.length; i++) {
        $('#inputGroupSelect01').append(`<option value="${clientes[i].cpf}">${clientes[i].nome}, ${clientes[i].cpf}</option>`);
    }
}

function simulaIngresso() {
    $('campoAdulto').value
    soma = ($('#campoAdulto').val() * dadosXMLHTTP.valorIngresso) + ($('#campoCrianca').val() * dadosXMLHTTP.valorIngresso / 2);
    $("#resultadoSimulacao").html(`R$ ${soma}`);
}