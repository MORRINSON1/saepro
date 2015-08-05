/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function display_black(idComponente) {
    var d = document.getElementById(idComponente);
    d.style.display = "block";
}

function display_none(idComponente, tipo){
    document.getElementById(idComponente).style.display = "none";
    limpiar(tipo);
}

function limpiar(tipo){
    
    switch (tipo){
        case "factores":
            document.getElementById("frmFactores:inputCodigo").value = "";
             document.getElementById("frmFactores:inputDescripcion").value = "";
            break;
            
        case "caracteristica":
          document.getElementById("frmFactores:inputCodigoCarac").value = "";
          document.getElementById("frmFactores:inputDescripcionCarac").value = "";
            break;
            
        case "indicador":
             document.getElementById("frmFactores:inputCodigoIndicador").value = "";
             document.getElementById("frmFactores:inputDescripcionIndicador").value = "";
            break;
            
        case "tipoPregunta":
            document.getElementById("frmFactores:inputCodigoTipoPregunta").value = "";
            document.getElementById("frmFactores:inputDescripcionTipoPregunta").value = "";
            break;
        
        case "alternativa":
            document.getElementById("frmFactores:descripcionAlternativa").value = "";
            document.getElementById("frmFactores:alternativa").value = "";
            document.getElementById("frmFactores:valorAlternativa").value = "";
            break;
            
        case "pregunta":
            document.getElementById("frmFactores:inputCodigoPregunta").value = "";
            document.getElementById("frmFactores:inputDescripcionPregunta").value = "";
            break;
            
        case "tipoPrograma":
            document.getElementById("frmFactores:inputCodigoTipoPrograma").value = "";
            document.getElementById("frmFactores:inputDescripcionTipoPrograma").value = "";
            break;
    }
    
    function cargarPage(url){
        alert("llego aca");
        $.noConflict();
        $("#frmFactores").load(url);
    }
}
