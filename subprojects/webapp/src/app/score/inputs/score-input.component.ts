import {
    Component,
    ComponentFactory,
    ComponentFactoryResolver,
    ComponentRef,
    EventEmitter,
    Input,
    OnInit,
    Output,
    ViewChild,
    ViewContainerRef } from '@angular/core';

import { ScoreInput } from './score-input';
import { ScorePointInputComponent } from './score-point-input.component';
import { ScoreTimeInputComponent } from './score-time-input.component';
import { ScoreUnknownInputComponent } from './score-unkown-input.component';

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

    @Output()
    public valueChanged = new EventEmitter<PartialScore>();

    @ViewChild('container', { read: ViewContainerRef })
    public container: ViewContainerRef;

    private _value: PartialScore;

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
                value: null
            };
        }

        let factory = this.resolveComponentFactory(this.type.type);
        if (factory) {
            let inputElement = this.container.createComponent(factory);
            inputElement.instance.value = this._value.value;
            inputElement.instance.valueChanged.subscribe((value: number) => {
                this.value.value = value;
                if (value) {
                    this.valueChanged.emit(this.value);
                } else {
                    this.valueChanged.emit(null);
                }
            });
        }
    }

    private resolveComponentFactory(type: ScoreIngredientType): ComponentFactory<ScoreInput> {
        switch (type) {
            case ScoreIngredientType.TIME:
                return this.componentFactoryResolver.resolveComponentFactory(ScoreTimeInputComponent);
            case ScoreIngredientType.POINTS:
                return this.componentFactoryResolver.resolveComponentFactory(ScorePointInputComponent);
            default:
                return this.componentFactoryResolver.resolveComponentFactory(ScoreUnknownInputComponent);
        }
    }
}