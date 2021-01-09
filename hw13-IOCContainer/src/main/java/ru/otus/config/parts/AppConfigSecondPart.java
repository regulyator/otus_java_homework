package ru.otus.config.parts;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.services.IOService;
import ru.otus.services.PlayerService;
import ru.otus.services.PlayerServiceImpl;

@AppComponentsContainerConfig(order = 1)
public class AppConfigSecondPart {

    @AppComponent(order = 1, name = "playerService")
    public PlayerService playerService(IOService ioService) {
        return new PlayerServiceImpl(ioService);
    }


}
