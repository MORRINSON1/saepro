/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.recursos;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarCamposObligatorios")
public class ValidatorCampos implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String label;
        HtmlInputText htmlInputText = (HtmlInputText) component;
        label = htmlInputText.getLabel();
        if (value.toString().trim().equals("")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", label + ": Campo Obligatorio"));
        }
    }

}
