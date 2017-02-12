import {
    Component,
    ComponentFactoryResolver,
    ComponentRef,
    HostBinding,
    ViewChild,
    ViewContainerRef,
    EventEmitter,
    Input,
    OnInit,
    Output } from '@angular/core';

import { ScoreInput } from './score-input';
import { ScorePointInputComponent } from './score-point-input.component';

import { PartialScore } from '../../model/partial-score';
import { ScoreIngredient } from '../../model/score-ingredient';
import { ScoreIngredientType } from '../../model/score-ingredient-type';

@Component({
    selector: 'score-input',
    templateUrl: 'score-input.component.html'
})
export class ScoreInputComponent implements OnInit {

    @Input()
    public type: ScoreIngredient;

    @Input()
    public name: string;
    
    private _value: PartialScore;

    @Output()
    public valueChanged = new EventEmitter<PartialScore>();

    @ViewChild('container', { read: ViewContainerRef })
    public container: ViewContainerRef;

    private input: ComponentRef<ScoreInput>;

    constructor(
        private componentFactoryResolver: ComponentFactoryResolver
     ) { }

    @Input()
    get value(): PartialScore {
        return this._value;
    }

    set value(value: PartialScore) {
        this._value = value;
    }

    public ngOnInit() {
        if (!this.value) {
            this.value = {
                name: this.type.name,
                value: 0
            };
        }

        if (this.type.type === ScoreIngredientType.POINTS) {
            let factory = this.componentFactoryResolver.resolveComponentFactory(ScorePointInputComponent);
            let inputElement = this.container.createComponent(factory);
            inputElement.instance.value = this._value.value;
            inputElement.instance.valueChanged.subscribe((value: number) => {
                this.value.value = value;
                this.valueChanged.emit(this.value);
            });
        }
    }
}