/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.recursos;

import java.io.Serializable;
import java.util.List;
import saepro.modelo.AlternativaRespuesta;
import saepro.modelo.Caracteristica;
import saepro.modelo.Factor;
import saepro.modelo.Indicador;
import saepro.modelo.TipoPregunta;

public class Recurso implements Serializable {

    public static boolean validarCodigoDescripcionIndicador(List<Indicador> listaIndicadores, String codigo) {
        boolean estado = false;
        try {
            if (!listaIndicadores.isEmpty()) {
                for (Indicador indicador : listaIndicadores) {
                    if (codigo.equals(indicador.getCodigo())) {
                        estado = true;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en el metodo validarCodigoDescripcionIndicador " + ex.getMessage());
        }
        return estado;
    }

    public static boolean validarCodigoDescripcionIndicador(List<Indicador> listaIndicadores, Indicador objIndicador) {
        boolean estado = false;
        try {
            if (!listaIndicadores.isEmpty()) {
                for (Indicador indicador : listaIndicadores) {
                    if (objIndicador.getCodigo().equals(indicador.getCodigo()) && objIndicador.getId() != indicador.getId()) {
                        estado = true;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en metodo validarCodigoDescripcionFactor " + ex.getMessage());
        }
        return estado;
    }

    //Metodo que verifica si existe un Factor con Codigo y descripcion iguales.
    public static boolean validarCodigoDescripcionFactor(List<Factor> listaFactores, String codigo, String descripcion) {
        boolean estado = false;
        try {
            if (!listaFactores.isEmpty()) {
                for (Factor factor : listaFactores) {
                    if (codigo.equals(factor.getCodigo()) || descripcion.equals(factor.getDescripcion())) {
                        estado = true;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en metodo validarCodigoDescripcionFactor " + ex.getMessage());
        }
        return estado;
    }

    public static boolean validarCodigoDescripcionFactor(List<Factor> listaFactores, Factor objFactor) {
        boolean estado = false;
        try {
            if (!listaFactores.isEmpty()) {
                for (Factor factor : listaFactores) {
                    if (objFactor.getCodigo().equals(factor.getCodigo()) && objFactor.getIdFactor() != factor.getIdFactor()) {
                        estado = true;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en metodo validarCodigoDescripcionFactor " + ex.getMessage());
        }
        return estado;
    }

    //Metodo que verifica si existe una Caracteristica con Codigo y descripcion iguales.
    public static boolean validarCodigoDescripcionCaracteristica(List<Caracteristica> listaCaracteristicas, String codigo, String descripcion) {
        boolean estado = false;
        try {
            if (!listaCaracteristicas.isEmpty()) {
                for (Caracteristica caracteristica : listaCaracteristicas) {
                    if (codigo.equals(caracteristica.getCodigo()) || descripcion.equals(caracteristica.getDescripcion())) {
                        estado = true;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en metodo validarCodigoDescripcionCaracteristica " + ex.getMessage());
        }
        return estado;
    }

    public static boolean validarCodigoDescripcionCaracteristica(List<Caracteristica> listaCaracteristicas, Caracteristica objCaracteristica) {
        boolean estado = false;

        try {
            if (!listaCaracteristicas.isEmpty()) {
                for (Caracteristica caracteristica : listaCaracteristicas) {
                    if (objCaracteristica.getCodigo().equals(caracteristica.getCodigo()) && objCaracteristica.getId() != caracteristica.getId()) {
                        estado = true;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en metodo validarCodigoDescripcionCaracteristica " + ex.getMessage());
        }
        return estado;
    }

    public static String validarAlternativa(List<AlternativaRespuesta> listaAlternativa, String alternativa, double valor) {
        String estado = "true";
        try {

            if (!listaAlternativa.isEmpty()) {

                for (AlternativaRespuesta alternativaRespuesta : listaAlternativa) {
                    if (alternativaRespuesta.getAlternativa().equals(alternativa)) {
                        estado = "Alternativa";
                        break;
                    } else if (alternativaRespuesta.getValor() == valor) {
                        estado = "Valor";
                        break;
                    }
                }
            }

        } catch (Exception ex) {

        }
        return estado;
    }

    public static String validarCamposVaciosAlternativa(AlternativaRespuesta alternativa) {
        String mensaje = "true";
        try {
           
            if (alternativa.getDescripcion().equals("") || alternativa.getDescripcion() == null) {
                mensaje = "Debe llenar el campo Descripcion";
            } else if (alternativa.getAlternativa().equals("") || alternativa.getDescripcion() == null) {
                mensaje = "Debe llenar el campo Alternativa";
            } else if (alternativa.getValor() == null) {
                mensaje = "Debe llenar el campo Valor";
            }

        } catch (Exception ex) {
            System.out.println("Error en metodo validarCamposVaciosAlternativa " + ex.getMessage());
        }
        return mensaje;
    }

    public static boolean validarTipoPregunta(List<TipoPregunta> listaTipoPregunta, String descripcion, String codigo) {
        boolean estado = false;
        try {

            if (!listaTipoPregunta.isEmpty()) {

                for (TipoPregunta tipoPregunta : listaTipoPregunta) {
                    if (tipoPregunta.getDescripcion().equals(descripcion) || tipoPregunta.getCodigo().equals(codigo)) {
                        estado = true;
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("Error en metodo validarTipoPregunta "+ex.getMessage());
        }
        return estado;
    }
}
