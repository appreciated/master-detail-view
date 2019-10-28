package com.github.appreciated.masterdetail;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.vaddon.CustomMediaQuery;
import org.vaddon.css.query.MediaQuery;
import org.vaddon.css.query.values.WidthAttributes;

@Tag("master-detail-view")
@JsModule("./com/github/appreciated/master-detail/master-detail-view.js")
public abstract class MasterDetailView<M extends Component & MasterView<Integer>, D extends Component & HasUrlParameter<Integer>, Integer> extends PolymerTemplate<TemplateModel> implements HasSize, HasUrlParameter<Integer> {

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
            UI.getCurrent().navigate(UI.getCurrent().getRouter().getUrl(this.getClass(), integer));
        } else {
            if (isMasterAndDetail != null && isMasterAndDetail) {
                try {
                    if (oldDetailView != null) {
                        getElement().removeChild(oldDetailView.getElement());
                    }
                    D instance = detailViewClass.newInstance();
                    instance.getElement().setAttribute("slot", "detail-content-slot");
                    instance.setParameter(null, integer);
                    getElement().appendChild(instance.getElement());
                    master.setActiveElement(integer);
                    oldDetailView = instance;
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
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
