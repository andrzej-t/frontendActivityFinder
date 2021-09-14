package com.myapp.frontendactivityfinder.views;

import com.myapp.frontendactivityfinder.client.BackendClient;
import com.myapp.frontendactivityfinder.domain.Activity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route
@Getter
public class MainView extends VerticalLayout {

    private BackendClient backendClient;

    H4 header = new H4("ACTIVITY FINDER... wyszukiwarka zajęć i zabaw dla najmłodszych");

    Span content = new Span("Witaj w aplikacji ACTIVITY FINDER! \nJest to narzędzie służące do wyszukiwania wszelkich aktywności, które \n" +
            "pomogą skutecznie zorganizować wolny czas dla twojego dziecka. \nZnajdziesz tu propozycje zarówno wspólnych rodzinnych zabaw, jak i \n" +
            "zajęć, które twoja pociecha może wykonywać samodzielnie.");

    NativeButton buttonInside = new NativeButton("Zamknij [x]");

    Notification notification = new Notification(content, buttonInside);

    Notification weatherNotif = new Notification();

    ComboBox<String> labelComboBox = new ComboBox<>();

    Grid<Activity> grid = new Grid(Activity.class);

    Button infoBtn = new Button("INFO", event -> {
        grid.setItems(Stream.empty());
        notification.setOpened(true);
        getFavouriteBtn().setEnabled(false);
        getLotteryBtn().setEnabled(false);
        getAllBtn().setEnabled(false);
    });

    Button allBtn = new Button("WSZYSTKIE", event -> {
        grid.setItems(backendClient.getAllActivities());
    });

    Button favouriteBtn = new Button("POPULARNE", event -> {
        grid.setItems(backendClient.getFavouriteActivities());
    });

    Button lotteryBtn = new Button("WYLOSUJ", event -> {
        grid.setItems(backendClient.getRandomActivity());
    });

    Button chngFiltersBtn = new Button("ZMIEŃ FILTR", event -> {
        grid.setItems(Stream.empty());
        getWhereRadioBtn().setReadOnly(false);
        getWhereRadioBtn().setValue("");
        getWhatKindRadioBtn().setReadOnly(false);
        getWhatKindRadioBtn().setValue("");
        getSeasonRadioBtn().setReadOnly(false);
        getSeasonRadioBtn().setValue("");
        getHowManyRadioBtn().setReadOnly(false);
        getHowManyRadioBtn().setValue("");
        getChngFiltersBtn().setEnabled(false);
    });

    RadioButtonGroup<String> howManyRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> whatKindRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> whereRadioBtn = new RadioButtonGroup<>();
    RadioButtonGroup<String> seasonRadioBtn = new RadioButtonGroup<>();
    TextField filterText = new TextField();
    HorizontalLayout menuLt = new HorizontalLayout(filterText, infoBtn, allBtn, favouriteBtn, lotteryBtn);
    HorizontalLayout bottomLt = new HorizontalLayout(whatKindRadioBtn, chngFiltersBtn, howManyRadioBtn, whereRadioBtn, seasonRadioBtn, chngFiltersBtn, labelComboBox);

    public MainView(BackendClient backendClient) {
        this.backendClient=backendClient;

        add(header);

        chngFiltersBtn.setEnabled(false);
        notification.setDuration(30000);
        buttonInside.addClickListener(event -> {
            notification.close();
            getFavouriteBtn().setEnabled(true);
            getLotteryBtn().setEnabled(true);
            getAllBtn().setEnabled(true);
        });
        notification.setPosition(Notification.Position.MIDDLE);

        howManyRadioBtn.setLabel("ILE OSÓB:");
        howManyRadioBtn.setItems("1", "2", "Więcej");
        howManyRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        howManyRadioBtn.setReadOnly(false);
        howManyRadioBtn.addValueChangeListener(event -> {
            if (event.getValue().equals("1")) {
                grid.setItems(backendClient.getOneActivities());
                whatKindRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("2")) {
                grid.setItems(backendClient.getTwoActivities());
                whatKindRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("Więcej")) {
                grid.setItems(backendClient.getMoreActivities());
                whatKindRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
        });

        whatKindRadioBtn.setLabel("RODZAJ:");
        whatKindRadioBtn.setItems("Artystyczne", "Edukacyjne", "Ruchowe", "Do auta");
        whatKindRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        whatKindRadioBtn.setReadOnly(false);
        whatKindRadioBtn.addValueChangeListener(event -> {
            if (event.getValue().equals("Artystyczne")) {
                grid.setItems(backendClient.getArtActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("Edukacyjne")) {
                grid.setItems(backendClient.getEducationalActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("Ruchowe")) {
                grid.setItems(backendClient.getMotionActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("Do auta")) {
                grid.setItems(backendClient.getInCarActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
        });

        seasonRadioBtn.setLabel("PORA ROKU:");
        seasonRadioBtn.setItems("Lato", "Zima");
        seasonRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        seasonRadioBtn.setReadOnly(false);
        seasonRadioBtn.addValueChangeListener(event -> {
            if (event.getValue().equals("Lato")) {
                grid.setItems(backendClient.getSummerActivities());
                whatKindRadioBtn.setReadOnly(true);
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("Zima")) {
                grid.setItems(backendClient.getWinterActivities());
                whatKindRadioBtn.setReadOnly(true);
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
        });

        whereRadioBtn.setLabel("GDZIE:");
        whereRadioBtn.setItems("Na zewnątrz", "Wewnątrz");
        whereRadioBtn.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        whereRadioBtn.setReadOnly(false);
        whereRadioBtn.addValueChangeListener(event -> {
            if (event.getValue().equals("Na zewnątrz")) {
                grid.setItems(backendClient.getOutdoorActivities());
                whatKindRadioBtn.setReadOnly(true);
                howManyRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
            if (event.getValue().equals("Wewnątrz")) {
                grid.setItems(backendClient.getIndoorActivities());
                whatKindRadioBtn.setReadOnly(true);
                howManyRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
            }
        });

        filterText.setPlaceholder("Wyszukaj po nazwie...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());

        grid.setColumns("name", "minTime", "maxTime");
        grid.getColumnByKey("name").setHeader("NAZWA");
        grid.getColumnByKey("minTime").setHeader("CZAS MIN.");
        grid.getColumnByKey("maxTime").setHeader("CZAS MAX.");

        grid.setItemDetailsRenderer(TemplateRenderer.<Activity>of(
                        "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
                                + "<div>[[item.description]]</div>"
                                + "</div>")
                .withProperty("description", Activity::getDescription)
                .withEventHandler("handleClick", activity -> {
                    grid.getDataProvider().refreshItem(activity);
                }));

        grid.setDetailsVisibleOnClick(false);
        grid.addColumn(new NativeButtonRenderer("OPIS", item -> grid.setDetailsVisible((Activity) item, !grid.isDetailsVisible((Activity) item))));

        labelComboBox.setItems("Białystok", "Bielsko Biała", "Chojnice", "Częstochowa", "Elbląg", "Gdańsk", "Gorzów", "Hel", "Jelenia Góra", "Kalisz", "Kasprowy Wierch", "Katowice", "Kętrzyn", "Kielce", "Kłodzko", "Koło", "Kołobrzeg", "Koszalin", "Kozienice", "Kraków", "Krosno", "Legnica", "Lesko", "Leszno", "Lębork", "Lublin", "Łeba", "Łódź",
                "Mikołajki", "Mława", "Nowy Sącz", "Olsztyn", "Opole", "Ostrołęka", "Piła", "Płock", "Poznań", "Przemyśl", "Racibórz", "Resko", "Rzeszów", "Sandomierz", "Siedlce", "Słubice", "Sulejów", "Suwałki", "Szczecin", "Szczecinek", "Śnieżka", "Świnoujście", "Tarnów", "Terespol", "Toruń", "Ustka", "Warszawa", "Wieluń", "Włodawa", "Wrocław", "Zakopane", "Zamość", "Zielona Góra");
        labelComboBox.setLabel("Aktualna pogoda: ");
        labelComboBox.addValueChangeListener(event -> {
            weatherNotif.open();
            weatherNotif.setPosition(Notification.Position.MIDDLE);
            weatherNotif.setText(backendClient.readWeather(event.getValue().replace('ą', 'a').replace('ć', 'c').replace('ę', 'e')
                    .replace('ł', 'l').replace('ń', 'n').replace('ó', 'o').replace('ś', 's').replace('ż', 'z').replaceAll("\\s+","").toLowerCase(Locale.ROOT)).toString());
            weatherNotif.setDuration(3000);
        });

        add(menuLt, grid);

        updateList();

        refresh();

        add(bottomLt);
    }

    public void refresh() {
        grid.setItems(backendClient.getAllActivities());
    }
    private void updateList() {
        grid.setItems(backendClient.getAllActivities().stream().filter(activity -> activity.getName().contains(filterText.getValue())).collect(Collectors.toList()));
    }
}