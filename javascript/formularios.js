/* Pontifícia Universidade Católica de Minas Gerais || Trabalho Interdisciplinar de Software - 2º período
    Membros:
    Filipe Iannarelli Caldeira
    Gabriel Vinicius Ramos da Silva
    Paulo Angelo Dias Barbosa
    Wesley Mouraria Pereira
*/

//Função que coleta os dados de determinado fornecedor e preenche na tela seus respectivos campos
function fornecedor() {
    if (document.getElementById('fornecedor').style.display == "none") {
        $("#formFornecedor")[0].reset();
        document.getElementsByClassName("btnAcao")[1].style.display = "none";
        document.getElementsByClassName("btnUpdate")[1].style.display = "none";
        document.getElementsByClassName("btnDelete")[1].style.display = "none";
        document.getElementsByClassName("btnOperacao")[0].style.display = "none";
        for (i = 3; i < $("#formFornecedor")[0].length; i++) {
            if (i != 4) {
                $("#formFornecedor")[0][i].disabled = true;
                $("#formFornecedor")[0][i].style.display = "";
                jQuery("label[for=" + $("#formFornecedor")[0][i].getAttribute("id") + "]")[0].style.display = "";
            }
            else {
                $("#formFornecedor")[0][i].style.display = "none";
                jQuery("label[for=" + $("#formFornecedor")[0][i].getAttribute("id") + "]")[0].style.display = "none";
                $(".colNovoNome").addClass("d-none");
                $(".colNome").removeClass("d-none");

            }
        }
        document.getElementById('fornecedor').style.display = "";
        document.getElementById('evento').style.display = "none";
        document.getElementById('cliente').style.display = "none";
    }
}

//Função que coleta os dados de determinado evento e preenche na tela seus respectivos campos
function evento() {
    if (document.getElementById('evento').style.display == "none") {
        $("#formEvento")[0].reset();
        document.getElementsByClassName("btnAcao")[0].style.display = "none";
        document.getElementsByClassName("btnIndicadores")[0].style.display = "none";
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
        document.getElementById('fornecedor').style.display = "none";
        document.getElementById('evento').style.display = "";
        document.getElementById('cliente').style.display = "none";
    }
}

//Função que coleta os dados de determinado cliente e preenche na tela seus respectivos campos
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
        document.getElementById('fornecedor').style.display = "none";
        document.getElementById('evento').style.display = "none";
        document.getElementById('cliente').style.display = "";
    }
}

//Função que habilita o form para cadastro, consulta ou atualização de determinado evento, cliente ou fornecedor
function habilitaForm(status, funcao, modulo) {
    let form = document.querySelector(`#form${modulo}`);
    let indiceForm;
    if (modulo == "Evento") {
        indiceForm = 0;
    }
    else if (modulo == "Cliente") {
        indiceForm = 1;
    }
    else if (modulo == "Fornecedor") {
        indiceForm = 2;
    }
    let btnAcao = document.getElementsByClassName("btnAcao")[indiceForm];
    if (status == true) {
        btnAcao.style.display = "";
        document.getElementsByClassName("btnIndicadores")[0].style.display = "none";
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
            document.getElementsByClassName("btnIndicadores")[indiceForm].style.display = "";
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