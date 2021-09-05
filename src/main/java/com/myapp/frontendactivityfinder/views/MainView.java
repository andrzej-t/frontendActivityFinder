package com.myapp.frontendactivityfinder.views;

import com.myapp.frontendactivityfinder.client.BackendClient;
import com.myapp.frontendactivityfinder.domain.Activity;
import com.myapp.frontendactivityfinder.service.ActivityService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {

    private BackendClient backendClient;
    @Autowired
    private ActivityService activityService;

    Grid grid = new Grid(Activity.class);
    Button infoBtn = new Button("INFO");
    Button allBtn = new Button("WSZYSTKIE");
    Button favouriteBtn = new Button("ULUBIONE");
    Button lotteryBtn = new Button("WYLOSUJ");
    Button planningBtn = new Button("ZAPLANUJ");
    Button rmvFiltersBtn = new Button("USUŃ FILTRY");
    NumberField minutesField = new NumberField();
    RadioButtonGroup<String> howManyRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> whatKindRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> whereRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> seasonRadioBtn = new RadioButtonGroup<>();
    TextField filterText = new TextField();
    HorizontalLayout menuLt = new HorizontalLayout(filterText,infoBtn, allBtn, favouriteBtn, lotteryBtn, planningBtn, minutesField);
    HorizontalLayout bottomLt = new HorizontalLayout(whatKindRadioBtn, rmvFiltersBtn, howManyRadioBtn, whereRadioBtn, seasonRadioBtn, rmvFiltersBtn);


    public MainView(BackendClient backendClient) {
        this.backendClient=backendClient;

        howManyRadioBtn.setLabel("ILE OSÓB:");
        howManyRadioBtn.setItems("1", "2", "Więcej");
        howManyRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        whatKindRadioBtn.setLabel("RODZAJ:");
        whatKindRadioBtn.setItems("Artystyczne", "Edukacyjne", "Ruchowe", "Do auta");
        whatKindRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        seasonRadioBtn.setLabel("PORA ROKU:");
        seasonRadioBtn.setItems("Lato", "Zima");
        seasonRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        whereRadioBtn.setLabel("GDZIE:");
        whereRadioBtn.setItems("Na zewnątrz", "Wewnątrz");
        whereRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        filterText.setPlaceholder("Wyszukaj po nazwie...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());

        grid.setColumns("name", "description", "favourite");
        grid.getColumnByKey("name").setHeader("Nazwa");
        grid.getColumnByKey("description").setHeader("Opis");
        grid.getColumnByKey("favourite").setHeader("Ulubione");

        add(menuLt, grid);

        updateList();

        refresh();

        add(bottomLt);
    }

    public void refresh() {
        grid.setItems(backendClient.getInCarActivities());
    }

    private void updateList() {
        grid.setItems(backendClient.getAllActivities().stream().filter(activity -> activity.getName().contains(filterText.getValue())).collect(Collectors.toList()));
    }

}
