var serverAddress = "http://127.0.0.1:880";
var dadosXMLHTTP;
var clientes;
var inscricao;

function executaXML(funcao, modulo) {
    let form = document.querySelector(`#form${modulo}`);
    let path;
    if (funcao == "create")
        path = serverAddress + `/cadastrar${modulo}`;
    else if (funcao == "read")
        path = serverAddress + `/consultar${modulo}`;
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

function operacoesEventos(funcao, modulo) {
    let form = document.querySelector(`#form${modulo}`);
    let path;
    if (funcao == "create")
        path = serverAddress + `/cadastrar${modulo}`;
    else if (funcao == "read")
        path = serverAddress + `/consultar${modulo}`;
    else if (funcao == "update")
        path = serverAddress + `/atualizar${modulo}`;
    else if (funcao == "delete")
        path = serverAddress + `/excluir${modulo}`;
    if (modulo == "Cliente")
        path = serverAddress + `/consultar${modulo}`;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", path, true);
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    var campos;
    if (modulo == "Cliente") {
        campos = null;
        xmlhttp.send(campos);
    } else if (funcao == "read") {
        campos = `${$.param(dadosXMLHTTP)}`;
        xmlhttp.send(campos);
    } else if (form.reportValidity()) {
        campos = `${$('#formInscricao').serialize()}&${$.param(dadosXMLHTTP)}&${$('#campoAdulto').attr("name")}=${$('#campoAdulto').val()}&${$('#campoCrianca').attr("name")}=${$('#campoCrianca').val()}`;
        xmlhttp.send(campos);
    }

    xmlhttp.onreadystatechange = function (e) {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status >= 200) {
                console.log("requisicao OK. \n" + xmlhttp.response);
                if (modulo == "Inscricao")
                    inscricao = JSON.parse(xmlhttp.response);
                else
                    dadosXMLHTTP = JSON.parse(xmlhttp.response);
                if (modulo == "Cliente") {
                    clientes = JSON.parse(xmlhttp.response);
                    preencheOperacoes(JSON.parse(xmlhttp.response));
                }
            } else {
                console.error("erro na requisicao. //" + xmlhttp.statusText);
            }
        }
    }
    console.log("apos o onreadystatechange");
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

function mostrarPainel(modulo, dados) {
    $("#modalTitle").html(`${modulo}.`);
    if (dados.length == 0) {
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

function preencheInscricao(l) {
    form = $('#formInscricao');
    $.each(inscricao[l], function (key, value) {
        $('[name=' + key + ']', form).val(value);
    });
    $('#inputGroupSelect01').val(inscricao[l].cliente.cpf);
    $("#myModal").modal("toggle");
}

function modalOperacoes() {
    operacoesEventos("read", "Inscricao");
    $("#modalTitle").html(`Inscrições.`);

    setTimeout(function () {
        $("#modalBody").html(function () {
            if (inscricao == undefined || inscricao.length == 0) {
                return "Lista Vazia.";
            } else {
                texto = "";
                for (i = 0; i < inscricao.length; i++) {
                    texto += `<div><a href='#' onclick='preencheInscricao(${i})'>${inscricao[i].cliente.nome}, CPF: ${inscricao[i].cliente.cpf}</a></div>`;
                }
                return texto;
            }
        });
        $("#myModal").modal();
    }, 500);
}

function simulaIngresso() {
    $('campoAdulto').value
    soma = ($('#campoAdulto').val() * dadosXMLHTTP.valorIngresso) + ($('#campoCrianca').val() * dadosXMLHTTP.valorIngresso / 2);
    $("#resultadoSimulacao").html(`R$ ${soma}`);
}