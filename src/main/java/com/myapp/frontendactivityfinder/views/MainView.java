package com.myapp.frontendactivityfinder.views;

import com.myapp.frontendactivityfinder.client.BackendClient;
import com.myapp.frontendactivityfinder.domain.Activity;
import com.myapp.frontendactivityfinder.domain.StatisticsDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route
@Getter
public class MainView extends VerticalLayout {

    BackendClient backendClient;

    @Autowired
    StatisticsDto statisticsDto;

    Integer clickCounter;

    Button thumbsUpButton = new Button(new Icon(VaadinIcon.THUMBS_UP), event -> {
        getThumbsUpButton().setDisableOnClick(true);
        statisticsDto.setLikeStat(1L);
        backendClient.updateStatistics(statisticsDto);
        Notification.show("Dzięki za lajka! Liczba polubień: " + backendClient.getLikes(), 1500, Notification.Position.MIDDLE);
        try {
            for (int i = 0; i < 400000; i++) {
                System.out.println(i);
            }
        } finally {
            getThumbsUpButton().setEnabled(true);
        }
    });

    Grid<Activity> grid = new Grid(Activity.class);

    H4 header = new H4("ACTIVITY FINDER wyszukiwarka zajęć i zabaw dla najmłodszych i nie tylko");

    Span content = new Span("Witaj w aplikacji ACTIVITY FINDER! Jest to narzędzie służące do wyszukiwania wszelkich aktywności, które " +
            "pomogą skutecznie zorganizować wolny czas dla Twojego dziecka. Znajdziesz tu propozycje zarówno wspólnych rodzinnych zabaw, jak i " +
            "zajęć, które Twoja pociecha może wykonywać samodzielnie. W zakładce \"ACTIVITY IN ENGLISH\" możesz również odnaleźć losowo wybrane propozycje zajęć dla dorosłych.");
    NativeButton buttonInside = new NativeButton("Zamknij");
    Notification notification = new Notification(content, buttonInside);

    NativeButton buttonInside1 = new NativeButton("Zamknij");
    Span content1 = new Span();
    Notification notification1 = new Notification(content1, buttonInside1);

    Select<String> placeholderSelect = new Select<>();

    NativeButton buttonInside2 = new NativeButton("Close");
    Span content2 = new Span();
    Notification notification2 = new Notification(content2, buttonInside2);
    Button boredBtn = new Button("ACTIVITY IN ENGLISH", event -> {
        getButtonInside2().getElement().getStyle().set("margin", "20% 30% 10% 38%");
        content2.setText(backendClient.readBored().getActivity() + "\n");
        grid.setItems(Stream.empty());
        notification2.setOpened(true);
        getFilterText().setEnabled(false);
        getInfoBtn().setEnabled(false);
        getAllBtn().setEnabled(false);
        getNewestBtn().setEnabled(false);
        getLotteryBtn().setEnabled(false);
        getGrid().setEnabled(false);
        getWhatKindRadioBtn().setReadOnly(true);
        getWhatKindRadioBtn().setValue("");
        getHowManyRadioBtn().setReadOnly(true);
        getHowManyRadioBtn().setValue("");
        getWhereRadioBtn().setReadOnly(true);
        getWhereRadioBtn().setValue("");
        getSeasonRadioBtn().setReadOnly(true);
        getSeasonRadioBtn().setValue("");
        getChngFiltersBtn().setEnabled(false);
        getBoredBtn().setEnabled(false);
        getPlaceholderSelect().setEnabled(false);
    });

    Button infoBtn = new Button("INFO", event -> {
        getButtonInside().getElement().getStyle().set("margin", "20% 30% 10% 38%");
        grid.setItems(Stream.empty());
        notification.setOpened(true);
        getFilterText().setEnabled(false);
        getInfoBtn().setEnabled(false);
        getAllBtn().setEnabled(false);
        getNewestBtn().setEnabled(false);
        getLotteryBtn().setEnabled(false);
        getGrid().setEnabled(false);
        getWhatKindRadioBtn().setReadOnly(true);
        getWhatKindRadioBtn().setValue("");
        getHowManyRadioBtn().setReadOnly(true);
        getHowManyRadioBtn().setValue("");
        getWhereRadioBtn().setReadOnly(true);
        getWhereRadioBtn().setValue("");
        getSeasonRadioBtn().setReadOnly(true);
        getSeasonRadioBtn().setValue("");
        getChngFiltersBtn().setEnabled(false);
        getBoredBtn().setEnabled(false);
        getPlaceholderSelect().setEnabled(false);
    });

    Button allBtn = new Button("WSZYSTKIE", event -> {
        grid.setItems(backendClient.getAllActivities());
        getWhatKindRadioBtn().setReadOnly(false);
        getWhatKindRadioBtn().setValue("");
        getHowManyRadioBtn().setReadOnly(false);
        getHowManyRadioBtn().setValue("");
        getWhereRadioBtn().setReadOnly(false);
        getWhereRadioBtn().setValue("");
        getSeasonRadioBtn().setReadOnly(false);
        getSeasonRadioBtn().setValue("");
        getChngFiltersBtn().setEnabled(false);
    });

    Button newestBtn = new Button("NAJNOWSZE", event -> {
        grid.setItems(backendClient.getNewestActivities());
        getWhatKindRadioBtn().setReadOnly(false);
        getWhatKindRadioBtn().setValue("");
        getHowManyRadioBtn().setReadOnly(false);
        getHowManyRadioBtn().setValue("");
        getWhereRadioBtn().setReadOnly(false);
        getWhereRadioBtn().setValue("");
        getSeasonRadioBtn().setReadOnly(false);
        getSeasonRadioBtn().setValue("");
        getChngFiltersBtn().setEnabled(false);
    });

    Button lotteryBtn = new Button("WYLOSUJ", event -> {
        grid.setItems(backendClient.getRandomActivity());
        getWhatKindRadioBtn().setReadOnly(false);
        getWhatKindRadioBtn().setValue("");
        getHowManyRadioBtn().setReadOnly(false);
        getHowManyRadioBtn().setValue("");
        getWhereRadioBtn().setReadOnly(false);
        getWhereRadioBtn().setValue("");
        getSeasonRadioBtn().setReadOnly(false);
        getSeasonRadioBtn().setValue("");
        getChngFiltersBtn().setEnabled(false);
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

    HorizontalLayout menuLt = new HorizontalLayout(filterText, infoBtn, allBtn, newestBtn, lotteryBtn);
    HorizontalLayout bottomLt = new HorizontalLayout(whatKindRadioBtn, chngFiltersBtn, howManyRadioBtn, whereRadioBtn, seasonRadioBtn, chngFiltersBtn, boredBtn, placeholderSelect, thumbsUpButton);

    public MainView(BackendClient backendClient) {

        this.backendClient=backendClient;

        thumbsUpButton.addClickListener(event -> {
            clickCounter = 0;
            if (thumbsUpButton.isDisableOnClick()) {
                clickCounter++;
            }
        });

        header.getElement().getStyle().set("color", "#535459");

        add(header);

        chngFiltersBtn.setEnabled(false);

        notification.setPosition(Notification.Position.MIDDLE);

        buttonInside.getElement().getStyle()
                        .set("color", "#f3f5f7")
                        .set("background", "#014af3");

        buttonInside.addClickListener(event -> {
            notification.close();
            getFilterText().setEnabled(true);
            getInfoBtn().setEnabled(true);
            getAllBtn().setEnabled(true);
            getNewestBtn().setEnabled(true);
            getLotteryBtn().setEnabled(true);
            getGrid().setEnabled(true);
            getWhatKindRadioBtn().setReadOnly(false);
            getWhatKindRadioBtn().setValue("");
            getHowManyRadioBtn().setReadOnly(false);
            getHowManyRadioBtn().setValue("");
            getWhereRadioBtn().setReadOnly(false);
            getWhereRadioBtn().setValue("");
            getSeasonRadioBtn().setReadOnly(false);
            getSeasonRadioBtn().setValue("");
            getChngFiltersBtn().setEnabled(false);
            getBoredBtn().setEnabled(true);
            getPlaceholderSelect().setEnabled(true);
        });

        buttonInside1.getElement().getStyle()
                .set("color", "#f3f5f7")
                .set("background", "#014af3");

        notification1.setPosition(Notification.Position.MIDDLE);
        buttonInside1.addClickListener(event -> {
            UI.getCurrent().getPage().reload();
            getFilterText().setEnabled(true);
            getInfoBtn().setEnabled(true);
            getAllBtn().setEnabled(true);
            getNewestBtn().setEnabled(true);
            getLotteryBtn().setEnabled(true);
            getWhatKindRadioBtn().setReadOnly(false);
            getWhatKindRadioBtn().setValue("");
            getHowManyRadioBtn().setReadOnly(false);
            getHowManyRadioBtn().setValue("");
            getWhereRadioBtn().setReadOnly(false);
            getWhereRadioBtn().setValue("");
            getSeasonRadioBtn().setReadOnly(false);
            getSeasonRadioBtn().setValue("");
            getChngFiltersBtn().setEnabled(false);
            getBoredBtn().setEnabled(true);
            getPlaceholderSelect().setEnabled(true);
            notification1.close();
        });

        buttonInside2.getElement().getStyle()
                .set("color", "#f3f5f7")
                .set("background", "#014af3");

        notification2.setPosition(Notification.Position.MIDDLE);
        buttonInside2.addClickListener(event -> {
            getFilterText().setEnabled(true);
            getInfoBtn().setEnabled(true);
            getAllBtn().setEnabled(true);
            getNewestBtn().setEnabled(true);
            getLotteryBtn().setEnabled(true);
            getGrid().setEnabled(true);
            getWhatKindRadioBtn().setReadOnly(false);
            getWhatKindRadioBtn().setValue("");
            getHowManyRadioBtn().setReadOnly(false);
            getHowManyRadioBtn().setValue("");
            getWhereRadioBtn().setReadOnly(false);
            getWhereRadioBtn().setValue("");
            getSeasonRadioBtn().setReadOnly(false);
            getSeasonRadioBtn().setValue("");
            getChngFiltersBtn().setEnabled(false);
            getBoredBtn().setEnabled(true);
            getPlaceholderSelect().setEnabled(true);
            notification2.close();
        });

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
                statisticsDto.setOneStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("2")) {
                grid.setItems(backendClient.getTwoActivities());
                whatKindRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setTwoStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("Więcej")) {
                grid.setItems(backendClient.getMoreActivities());
                whatKindRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setMoreStat(1L);
                backendClient.updateStatistics(statisticsDto);
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
                statisticsDto.setArtStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("Edukacyjne")) {
                grid.setItems(backendClient.getEducationalActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setEduStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("Ruchowe")) {
                grid.setItems(backendClient.getMotionActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setMotionStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("Do auta")) {
                grid.setItems(backendClient.getInCarActivities());
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setCarStat(1L);
                backendClient.updateStatistics(statisticsDto);
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
                statisticsDto.setSummerStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("Zima")) {
                grid.setItems(backendClient.getWinterActivities());
                whatKindRadioBtn.setReadOnly(true);
                howManyRadioBtn.setReadOnly(true);
                whereRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setWinterStat(1L);
                backendClient.updateStatistics(statisticsDto);
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
                statisticsDto.setOutdoorStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
            if (event.getValue().equals("Wewnątrz")) {
                grid.setItems(backendClient.getIndoorActivities());
                whatKindRadioBtn.setReadOnly(true);
                howManyRadioBtn.setReadOnly(true);
                seasonRadioBtn.setReadOnly(true);
                chngFiltersBtn.setEnabled(true);
                statisticsDto.setIndoorStat(1L);
                backendClient.updateStatistics(statisticsDto);
            }
        });

        filterText.setPlaceholder("Wyszukaj po nazwie...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> {
            updateList();
            getWhatKindRadioBtn().setReadOnly(false);
            getWhatKindRadioBtn().setValue("");
            getHowManyRadioBtn().setReadOnly(false);
            getHowManyRadioBtn().setValue("");
            getWhereRadioBtn().setReadOnly(false);
            getWhereRadioBtn().setValue("");
            getSeasonRadioBtn().setReadOnly(false);
            getSeasonRadioBtn().setValue("");
            getChngFiltersBtn().setEnabled(false);
        });

        grid.setColumns("name", "minTime", "maxTime");
        grid.getColumnByKey("name").setHeader("NAZWA");
        grid.getColumnByKey("minTime").setHeader("CZAS MIN.");
        grid.getColumnByKey("maxTime").setHeader("CZAS MAX.");

        grid.setItemDetailsRenderer(TemplateRenderer.<Activity>of(
                        "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
                                + "<div>[[item.description]]</div>"
                                + "</div>")
                .withProperty("description", Activity::getDescription)
                .withEventHandler("handleClick", activity -> grid.getDataProvider().refreshItem(activity)));

        grid.setDetailsVisibleOnClick(false);
        grid.addColumn(new NativeButtonRenderer("OPIS", item -> grid.setDetailsVisible((Activity) item, !grid.isDetailsVisible((Activity) item))));

        placeholderSelect.setItems("Białystok", "Bielsko Biała", "Chojnice", "Częstochowa", "Elbląg", "Gdańsk", "Gorzów", "Hel", "Jelenia Góra", "Kalisz", "Kasprowy Wierch", "Katowice", "Kętrzyn", "Kielce", "Kłodzko", "Koło", "Kołobrzeg", "Koszalin", "Kozienice", "Kraków", "Krosno", "Legnica", "Lesko", "Leszno", "Lębork", "Lublin", "Łeba", "Łódź",
                "Mikołajki", "Mława", "Nowy Sącz", "Olsztyn", "Opole", "Ostrołęka", "Piła", "Płock", "Poznań", "Przemyśl", "Racibórz", "Resko", "Rzeszów", "Sandomierz", "Siedlce", "Słubice", "Sulejów", "Suwałki", "Szczecin", "Szczecinek", "Śnieżka", "Świnoujście", "Tarnów", "Terespol", "Toruń", "Ustka", "Warszawa", "Wieluń", "Włodawa", "Wrocław", "Zakopane", "Zamość", "Zielona Góra");
        placeholderSelect.setPlaceholder("Wybierz miasto");
        placeholderSelect.setLabel("AKTUALNA POGODA: ");
        placeholderSelect.addValueChangeListener(event -> {
            getButtonInside1().getElement().getStyle().set("margin", "20% 30% 10% 38%");
            grid.setItems(Stream.empty());
            notification1.open();
            notification1.setPosition(Notification.Position.MIDDLE);
            content1.setText(backendClient.readWeather(event.getValue().replace('ą', 'a').replace('ć', 'c').replace('ę', 'e')
                    .replace('ł', 'l').replace('ń', 'n').replace('ó', 'o').replace('ś', 's').replace('Ś', 'S').replace('Ł', 'L').replace('ż', 'z').replace('ź', 'z').replaceAll("\\s+","").toLowerCase(Locale.ROOT)).toString());
            getFilterText().setEnabled(false);
            getInfoBtn().setEnabled(false);
            getAllBtn().setEnabled(false);
            getNewestBtn().setEnabled(false);
            getLotteryBtn().setEnabled(false);
            getWhatKindRadioBtn().setReadOnly(true);
            getWhatKindRadioBtn().setValue("");
            getHowManyRadioBtn().setReadOnly(true);
            getHowManyRadioBtn().setValue("");
            getWhereRadioBtn().setReadOnly(true);
            getWhereRadioBtn().setValue("");
            getSeasonRadioBtn().setReadOnly(true);
            getSeasonRadioBtn().setValue("");
            getChngFiltersBtn().setEnabled(false);
            getBoredBtn().setEnabled(false);
            getPlaceholderSelect().setEnabled(false);
        });

        add(menuLt, grid);

        updateList();

        refresh();

        add(bottomLt);
    }

    public void refresh() {
        grid.setItems(Stream.empty());
    }
    private void updateList() {
        grid.setItems(backendClient.getAllActivities().stream().filter(activity -> activity.getName().contains(filterText.getValue())).collect(Collectors.toList()));
    }
}