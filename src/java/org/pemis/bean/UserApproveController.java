/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pemis.bean;

import org.pemis.facade.InstitutionFacade;
import org.pemis.facade.PersonFacade;
import org.pemis.facade.LocationFacade;
import org.pemis.facade.UnitFacade;
import org.pemis.facade.WebUserFacade;
import org.pemis.facade.AreaFacade;
import org.pemis.entity.Area;
import org.pemis.entity.Institution;
import org.pemis.entity.Person;
import org.pemis.entity.Location;
import org.pemis.entity.WebUser;
import org.pemis.entity.Unit;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@RequestScoped
public class UserApproveController implements Serializable {

    DataModel<WebUser> toApproveUsers;
    DataModel<WebUser> users;
    WebUser selectedUser;
    Person selectedPerson;
    //
    @EJB
    WebUserFacade userFacade;
    @EJB
    AreaFacade areaFacade;
    @EJB
    InstitutionFacade institutionFacade;
    @EJB
    UnitFacade unitFacade;
    @EJB
    LocationFacade locationFacade;
    @EJB
    PersonFacade personFacade;
    //
    String activateComments;
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    DataModel<Area> areas;
    DataModel<Institution> institutions;
    DataModel<Unit> units;
    DataModel<Location> locations;
    @ManagedProperty(value = "#{imageController}")
    private ImageController imageController;

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public PersonFacade getPersonFacade() {
        return personFacade;
    }

    public void setPersonFacade(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    public ImageController getImageController() {
        return imageController;
    }

    public void setImageController(ImageController imageController) {
        this.imageController = imageController;
    }

    public String viewPerImage() {
        System.out.println("VIew Per Img");
        System.out.println("VIew Per Img" + selectedUser.getWebUserPerson().getName());
        imageController.setPerson(selectedUser.getWebUserPerson());
        System.out.println("person_image");
        return "person_image";
    }

    public DataModel<WebUser> getUsers() {
        String temSql;
        temSql = "SELECT a FROM WebUser a WHERE a.retired=false ORDER BY a.name ";
        return new ListDataModel<WebUser>(getUserFacade().findBySQL(temSql));
    }

    public void setUsers(DataModel<WebUser> users) {
        this.users = users;
    }

    public AreaFacade getAreaFacade() {
        return areaFacade;
    }

    public void setAreaFacade(AreaFacade areaFacade) {
        this.areaFacade = areaFacade;
    }

    public DataModel<Area> getAreas() {
        String temSql;
        temSql = "SELECT a FROM Area a WHERE a.retired=false ORDER BY a.name ";
        return new ListDataModel<Area>(getAreaFacade().findBySQL(temSql));
    }

    public void setAreas(DataModel<Area> areas) {
        this.areas = areas;
    }

    public InstitutionFacade getInstitutionFacade() {
        return institutionFacade;
    }

    public void setInstitutionFacade(InstitutionFacade institutionFacade) {
        this.institutionFacade = institutionFacade;
    }

    public DataModel<Institution> getInstitutions() {
        String temSql;
        temSql = "SELECT a FROM Institution a WHERE a.retired=false ORDER BY a.name ";
        return new ListDataModel<Institution>(getInstitutionFacade().findBySQL(temSql));
    }

    public void setInstitutions(DataModel<Institution> institutions) {
        this.institutions = institutions;
    }

    public LocationFacade getLocationFacade() {
        return locationFacade;
    }

    public void setLocationFacade(LocationFacade locationFacade) {
        this.locationFacade = locationFacade;
    }

    public DataModel<Location> getLocations() {
        String temSql;
        temSql = "SELECT a FROM Location a WHERE a.retired=false ORDER BY a.name ";
        return new ListDataModel<Location>(getLocationFacade().findBySQL(temSql));
    }

    public void setLocations(DataModel<Location> locations) {
        this.locations = locations;
    }

    public UnitFacade getUnitFacade() {
        return unitFacade;
    }

    public void setUnitFacade(UnitFacade unitFacade) {
        this.unitFacade = unitFacade;
    }

    public DataModel<Unit> getUnits() {
        String temSql;
        temSql = "SELECT a FROM Unit a WHERE a.retired=false ORDER BY a.name ";

        return new ListDataModel<Unit>(getUnitFacade().findBySQL(temSql));
    }

    public void setUnits(DataModel<Unit> units) {
        this.units = units;
    }

    /**
     * Creates a new instance of UserApproveController
     */
    public UserApproveController() {
    }

    public String getActivateComments() {
        return activateComments;
    }

    public void setActivateComments(String activateComments) {
        this.activateComments = activateComments;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public WebUser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(WebUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public DataModel<WebUser> getToApproveUsers() {
        String temSQL;
        temSQL = "SELECT u FROM WebUser u WHERE u.retired=false AND u.activated=false";
        List<WebUser> lst;
        lst = getUserFacade().findBySQL(temSQL);
        return new ListDataModel<WebUser>(lst);
    }

    public void setToApproveUsers(DataModel<WebUser> toApproveUsers) {
        this.toApproveUsers = toApproveUsers;
    }

    public WebUserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(WebUserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public void addUserToPerson() {
    }

    public void saveUser() {
        if (selectedUser == null) {
            JsfUtil.addErrorMessage("Please select a user");
            return;
        }
        userFacade.edit(selectedUser);
        selectedUser = null;
        JsfUtil.addSuccessMessage("Successfully activated");
    }

    public void approveUser() {
        if (selectedUser == null) {
            JsfUtil.addErrorMessage("Please select a user to approve");
            return;
        }
        selectedUser.setActivated(true);
        selectedUser.setActivatedAt(Calendar.getInstance().getTime());
        selectedUser.setActivator(sessionController.loggedUser);
        selectedUser.setActivateComments(activateComments);
        userFacade.edit(selectedUser);
        selectedUser = null;
        JsfUtil.addSuccessMessage("Successfully activated");
    }

    public void removeUser() {
        if (selectedUser == null) {
            JsfUtil.addErrorMessage("Please select a user to remove");
            return;
        }
        selectedUser.setActivated(false);
        selectedUser.setRetired(true);
        selectedUser.setRetiredAt(Calendar.getInstance().getTime());
        selectedUser.setRetirer(sessionController.loggedUser);
        selectedUser.setRetireComments(activateComments);
        userFacade.edit(selectedUser);

        selectedUser = null;
        JsfUtil.addSuccessMessage("Successfully activated");
    }

}
