package io.glenn.darksky.web;

import io.glenn.darksky.data.Location;
import io.glenn.darksky.data.Weather;
import io.glenn.darksky.services.LocationRepository;
import io.glenn.darksky.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class WeatherController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String main(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("location", locationRepository.findAll().get(0).getName());
        return "redirect:/weather";
    }

    @GetMapping("/weather")
    public String weather(@RequestParam Optional<String> location, Model model) {
        List<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        Weather weather = weatherService.getWeather(locations.get(0));
        model.addAttribute("weather", weather);
        return "index";
    }
}
