package com.myapp.frontendactivityfinder.views;

import com.myapp.frontendactivityfinder.domain.Activity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {

    Button infoBtn = new Button("INFO");
    Button allBtn = new Button("WSZYSTKIE");
    Button favouriteBtn = new Button("ULUBIONE");
    Button lotteryBtn = new Button("WYLOSUJ");
    Button planningBtn = new Button("ZAPLANUJ");
    Button rmvFiltersBtn = new Button("USUŃ FILTRY");
    Button searchingBtn = new Button("WYSZUKAJ");
    NumberField minutesField = new NumberField();
    RadioButtonGroup<String> howManyRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> whatKindRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> whereRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> seasonRadioBtn = new RadioButtonGroup<>();
    Grid resultsGrid = new Grid(Activity.class);
    HorizontalLayout upperMenuLt = new HorizontalLayout(infoBtn, allBtn, favouriteBtn, lotteryBtn, planningBtn, minutesField);
    VerticalLayout filterCol1 = new VerticalLayout(seasonRadioBtn, whatKindRadioBtn, rmvFiltersBtn);
    VerticalLayout filterCol2 = new VerticalLayout(howManyRadioBtn, whereRadioBtn);

    public MainView() throws NullPointerException {
        howManyRadioBtn.setLabel("ILE OSÓB:");
        howManyRadioBtn.setItems("1", "2", "Więcej");
        howManyRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        howManyRadioBtn.setValue("1");

        whatKindRadioBtn.setLabel("RODZAJ:");
        whatKindRadioBtn.setItems("Artystyczne", "Edukacyjne", "Ruchowe", "Do auta", "Dowolne");
        whatKindRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        whatKindRadioBtn.setValue("Artystyczne");

        seasonRadioBtn.setLabel("PORA ROKU:");
        seasonRadioBtn.setItems("Lato", "Zima");
        seasonRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        seasonRadioBtn.setValue("Lato");

        whereRadioBtn.setLabel("GDZIE:");
        whereRadioBtn.setItems("Na zewnątrz", "Wewnątrz");
        whereRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        whereRadioBtn.setValue("Na zewnątrz");

        add(upperMenuLt);

        HorizontalLayout centerLt = new HorizontalLayout(resultsGrid, filterCol1, filterCol2);
        add(centerLt);
        centerLt.setSizeFull();
        resultsGrid.setWidth("550%");
        add(searchingBtn);
    }

}
