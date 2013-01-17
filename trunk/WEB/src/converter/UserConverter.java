package converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.cpeeterprise.BeanUserRemote;

import model.User;

@ManagedBean(name="userConverter")
public class UserConverter implements Converter {
 
    @EJB
    private BeanUserRemote userRemote;
 
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return userRemote.findUser(Integer.parseInt(value));
    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((User) value).getUsrId());
    }

}