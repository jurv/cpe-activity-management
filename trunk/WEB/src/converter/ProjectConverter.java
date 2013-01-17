package converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.cpeeterprise.BeanProjectRemote;

import model.Project;

@ManagedBean(name="projectConverter")
public class ProjectConverter implements Converter {
 
    @EJB
    private BeanProjectRemote projectRemote;
 
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return projectRemote.findProject(Integer.parseInt(value));
    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Project)value).getPrjId());
    }

}