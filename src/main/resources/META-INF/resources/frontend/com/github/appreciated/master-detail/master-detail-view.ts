import {html} from '@polymer/polymer/polymer-element.js';
import {css, customElement, LitElement} from "lit-element";

@customElement('master-detail-view')
export class MasterDetailView extends LitElement {

    static get styles() {
        return [css`
            #master-wrapper {
                display: flex;
                width: 100%;
                height: 100%;
            }

            #master-content {
                height: 100%;
                transition: width 1s;
                overflow: auto;
                flex: 1 1 150px;
            }

            #detail-content {
                flex: 4 1;
                height: 100%;
                overflow: auto;
            }

            @media (max-width: 600px) {
                #detail-content {
                    display: none;
                }
            }
        `,
        ];
    }

    render() {
        return html`
            <div id="master-wrapper">
                <div id="master-content">
                    <slot name="master-content-slot"></slot>
                </div>
                <div id="detail-content">
                    <slot name="detail-content-slot"></slot>
                </div>
            </div>`;
    }
}