var serverAddress = "http://127.0.0.1:880";
var dadosXMLHTTP;
var clientes;
var inscricao;

function sendXML(path, dadosXML) {
    var xmlhttp = new XMLHttpRequest();
    console.log(dadosXML);

    return new Promise((resolve, reject) => {

        xmlhttp.onreadystatechange = (e) => {
            if (xmlhttp.readyState !== 4) {
                return;
            }

            if (xmlhttp.status >= 200) {
                console.log(JSON.parse(xmlhttp.response));
                resolve(JSON.parse(xmlhttp.responseText));
            } else {
                console.warn('request_error');
            }
        };
        xmlhttp.open("POST", path, true);
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send(dadosXML);
    });
}

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
    var campos;
    if (funcao == "create") {
        path = serverAddress + `/cadastrar${modulo}`;
        if (modulo == "Inscricao" && form.reportValidity())
            campos = `${$('#formInscricao').serialize()}&${$.param(dadosXMLHTTP)}&${$('#campoAdulto').attr("name")}=${$('#campoAdulto').val()}&${$('#campoCrianca').attr("name")}=${$('#campoCrianca').val()}`;
    }
    else if (funcao == "read") {
        path = serverAddress + `/consultar${modulo}`;
        if (modulo == "Inscricao")
            campos = `${$.param(dadosXMLHTTP)}`;
        if (modulo == "Cliente")
            campos = null;
    }
    else if (funcao == "update")
        path = serverAddress + `/atualizar${modulo}`;
    else if (funcao == "delete")
        path = serverAddress + `/excluir${modulo}`;

sendXML(path, campos).then(res => {
    if (modulo == "Inscricao")
        inscricao = JSON.parse(res);
    else
        dadosXMLHTTP = res;
    if (modulo == "Cliente") {
        clientes = res;
        preencheOperacoes(res);
    }
});
}

function consultaRegistro(modulo, indice) {
    $("#myModal").modal('toggle');
    let form = document.querySelector(`#form${modulo}`);
    let indiceForm = indice;
    if (modulo == "Evento") {
        evento();
        //indiceForm = 0;
    }
    else if (modulo == "Cliente") {
        cliente();
        //indiceForm = 1;
    }
    else if (modulo == "Fornecedor") {
        fornecedor();
        //indiceForm = 2;
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
    $("#formFornecedorNovoCNPJ").val(json[indice].cnpj);
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

    } else if (modulo == "Fornecedor") {
        $("#modalBody").html(function () {
            texto = "";
            for (i = 0; i < dados.length; i++) {
                texto += `<div><a href='#' onclick='consultaRegistro("${modulo}",${i})'>${dados[i].nome} - CNPJ: ${dados[i].cnpj}</a></div>`;
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
    sendXML(serverAddress+"/consultarInscricao", $.param(dadosXMLHTTP)).then(res => {
        inscricao = res;
        $("#modalTitle").html(`Inscrições.`);
        $("#modalBody").html(function () {
            if (res == undefined || res.length == 0) {
                return "Lista Vazia.";
            } else {
                texto = "";
                for (i = 0; i < res.length; i++) {
                    texto += `<div><a href='#' onclick='preencheInscricao(${i})'>${res[i].cliente.nome}, CPF: ${res[i].cliente.cpf}</a></div>`;
                }
                return texto;
            }
        });
        $("#myModal").modal();
    });
}

function simulaIngresso() {
    $('campoAdulto').value
    soma = ($('#campoAdulto').val() * dadosXMLHTTP.valorIngresso) + ($('#campoCrianca').val() * dadosXMLHTTP.valorIngresso / 2);
    $("#resultadoSimulacao").html(`R$ ${soma}`);
}

function addCampoCronograma() {
    $('#formCronograma').append(`
    <div class="input-group">
        <input type="text" class="form-control" required>
        <input type="datetime-local" class="form-control col-4" required>
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" onclick="excluirCampoCronograma(this)">Excluir</button>
        </div>
    </div>
    `);
}

function enviaCronograma() {
    form = $('#formCronograma')[0];
    if (form.reportValidity()) {
        map = new Map();
        for (var i = 0; i < form.length; i += 3) {
            map.set(form[i + 1].value, form[i].value);
        }
        json = $.param(Array.from(map).reduce((obj, [key, value]) => {
            obj[key] = value;
            return obj;
        }, {}));
        sendXML(serverAddress+"/cadastrarCronograma", `nome=${dadosXMLHTTP.nome}&${json}`);
    }
}

function listarCronograma() {
    form = $('#formCronograma')[0];
    sendXML(serverAddress+"/consultarCronograma", $.param(dadosXMLHTTP)).then(res => {
        indice = Object.keys(res);
        for (let i = 0; i < res.length; i++) {
            form[(i * 3)].value = res[indice[i]];
            form[((i * 3) + 1)].value = indice[i];
            if (i != 0) addCampoCronograma((i * 3));
        }
    });
}

function excluirCampoCronograma(element) {
    form = $('#formCronograma')[0];
    indice = null;
    for (var i = 2; i < form.length; i += 3) {
        if (form[i] == element) {
            $('#formCronograma')[0][i - 2].remove();
            $('#formCronograma')[0][i - 2].remove();
            $('#formCronograma')[0][i - 2].remove();
            break;
        }
    }
}
