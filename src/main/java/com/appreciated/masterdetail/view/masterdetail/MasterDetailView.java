package com.appreciated.masterdetail.view.masterdetail;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.vaddon.CustomMediaQuery;

@Tag("master-detail-view")
@HtmlImport("frontend://com/github/appreciated/master-detail/master-detail-view.html")
public class MasterDetailView extends PolymerTemplate<TemplateModel> implements HasSize {

    @Id("master-content")
    Div masterContent;

    @Id("detail-content")
    Div detailContent;

    boolean isMasterAndDetail = false;
    boolean isAttached = false;

    private Component oldDetailView;
    private CustomMediaQuery isMasterAndDetailQuery;

    public MasterDetailView() {
        setSizeFull();
    }

    public void setMaster(Component component) {
        component.getElement().setAttribute("slot", "master-content-slot");
        getElement().appendChild(component.getElement());

        isMasterAndDetailQuery = new CustomMediaQuery(aBoolean -> setMasterAndDetail(!aBoolean));
        isMasterAndDetailQuery.setQuery("(max-width: 600px)");
        getElement().appendChild(isMasterAndDetailQuery.getElement());
    }

    private void setMasterAndDetail(boolean masterAndDetail) {
        if (isAttached) {
            isMasterAndDetail = masterAndDetail;
            System.out.println("isMasterAndDetail: " + masterAndDetail);
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        isAttached = true;
    }

    /**
     *
     */
    public <T, C extends Component & HasUrlParameter<T>> void setDetail(Class<? extends C> navigationTarget, T parameter) {
        if (oldDetailView != null) {
            getElement().removeChild(oldDetailView.getElement());
        }
        if (isMasterAndDetail) {
            try {
                Component instance = navigationTarget.newInstance();
                instance.getElement().setAttribute("slot", "detail-content-slot");
                getElement().appendChild(instance.getElement());
                ((HasUrlParameter<T>) instance).setParameter(null, parameter);
                oldDetailView = instance;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            UI.getCurrent().navigate(navigationTarget, parameter);
        }
    }

    /**
     * @return
     */
    public Div getMasterContent() {
        return masterContent;
    }

    /**
     * @return
     */
    public Div getDetailContent() {
        return detailContent;
    }
}
