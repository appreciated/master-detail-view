package com.github.appreciated.masterdetail;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinService;
import org.vaddon.CustomMediaQuery;
import org.vaddon.css.query.MediaQuery;
import org.vaddon.css.query.values.WidthAttributes;

@Tag("master-detail-view")
@JsModule("./com/github/appreciated/master-detail/master-detail-view.ts")
public abstract class MasterDetailView<M extends Component & MasterView<Integer>, D extends Component & HasUrlParameter<Integer>, Integer> extends LitTemplate implements HasSize, HasUrlParameter<Integer> {

    @Id("master-content")
    Div masterContent;

    @Id("detail-content")
    Div detailContent;

    Boolean isMasterAndDetail = null;

    private CustomMediaQuery isMasterAndDetailQuery;
    private Class<D> detailViewClass;
    private Component oldDetailView;
    private Integer currentParameter;
    private M master;

    public MasterDetailView() {
        setSizeFull();
        isMasterAndDetailQuery = new CustomMediaQuery(aBoolean -> setMasterAndDetail(!aBoolean));
        isMasterAndDetailQuery.setQuery(new MediaQuery(new WidthAttributes.MaxWidth("600px")));
        getElement().appendChild(isMasterAndDetailQuery.getElement());
    }

    private void setMasterAndDetail(boolean masterAndDetail) {
        if (isMasterAndDetail == null) {
            isMasterAndDetail = masterAndDetail;
            setParameter(null, currentParameter);
        } else {
            isMasterAndDetail = masterAndDetail;
            if (master != null) {
                master.onMasterStateChanged(isMasterAndDetail);
            }
        }
        if (oldDetailView == null && masterAndDetail) {
            setParameter(null, currentParameter);
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer integer) {
        if (currentParameter != integer) {
            currentParameter = integer;
            final String url = RouteConfiguration.forSessionScope().getUrl(this.getClass(), integer);
            UI.getCurrent().navigate(url);
        } else {
            if (isMasterAndDetail != null && isMasterAndDetail) {
                if (oldDetailView != null) {
                    getElement().removeChild(oldDetailView.getElement());
                }
                D instance = VaadinService.getCurrent().getInstantiator().createComponent(detailViewClass);
                instance.getElement().setAttribute("slot", "detail-content-slot");
                instance.setParameter(null, integer);
                getElement().appendChild(instance.getElement());
                master.setActiveElement(integer);
                oldDetailView = instance;
            }
        }
    }

    public void setMaster(M component) {
        master = component;
        component.getElement().setAttribute("slot", "master-content-slot");
        getElement().appendChild(component.getElement());
        component.setNavigationListener(parameter -> {
            if (isMasterAndDetail != null && isMasterAndDetail) {
                setParameter(null, parameter);
            } else {
                UI.getCurrent().navigate(detailViewClass, parameter);
            }
        });
    }

    public void setDetail(Class<D> detailClass) {
        this.detailViewClass = detailClass;
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
