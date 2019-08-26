import {html} from '@polymer/polymer/polymer-element.js';

class MasterDetailView extends Polymer.Element {
    static get template() {
        return html`<style>
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
        </style>
        <div id="master-wrapper">
            <div id="master-content">
                <slot name="master-content-slot"></slot>
            </div>
            <div id="detail-content">
                <slot name="detail-content-slot"></slot>
            </div>
        </div>`;
    }

    static get is() {
        return 'master-detail-view'
    }
}

customElements.define(MasterDetailView.is, MasterDetailView);