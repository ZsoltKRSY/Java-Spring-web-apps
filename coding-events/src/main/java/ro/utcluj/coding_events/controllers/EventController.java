package ro.utcluj.coding_events.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.coding_events.data.EventCategoryRepository;
import ro.utcluj.coding_events.data.EventRepository;
import ro.utcluj.coding_events.data.TagRepository;
import ro.utcluj.coding_events.models.Event;
import ro.utcluj.coding_events.models.EventCategory;
import ro.utcluj.coding_events.models.Tag;
import ro.utcluj.coding_events.models.dto.TagDTO;

import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null){
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        }
        else{
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "All Events");
                model.addAttribute("events", eventRepository.findAll());
            }
            else{
                EventCategory category = result.get();
                model.addAttribute("title", "Events in Category " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }

        return "events/index";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam(required = false) Integer eventId, Model model){
        if(eventId == null){
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());

            return "redirect:/events";
        }
        else {
            Optional<Event> result = eventRepository.findById(eventId);

            if (result.isEmpty()) {
                model.addAttribute("title", "All Events");
                model.addAttribute("events", eventRepository.findAll());

                return "redirect:/events";
            } else {
                Event event = result.get();
                model.addAttribute("title", event.getName() + " details");
                model.addAttribute("event", event);
                model.addAttribute("tags", event.getTags());
            }
        }

        return "events/detail";
    }

    // /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    // /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            model.addAttribute("categories", eventCategoryRepository.findAll());
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:/events";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam(required = false) Integer eventId, Model model) {
        if(eventId == null){
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());

            return "redirect:/events";
        }
        else{
            Optional<Event> result = eventRepository.findById(eventId);
            if(result.isEmpty()){
                model.addAttribute("title", "All Events");
                model.addAttribute("events", eventRepository.findAll());

                return "redirect:/events";
            }
            else{
                Event event = result.get();
                model.addAttribute("title", "Add Tag to " + event.getName());
                model.addAttribute("tags", tagRepository.findAll());
                model.addAttribute("event", event);
                TagDTO eventTag = new TagDTO();
                eventTag.setEvent(event);
                model.addAttribute("eventTag", eventTag);
            }
        }

        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid TagDTO eventTag, Errors errors, Model model) {
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            System.out.println("not asd\n");
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }

            return "redirect:detail?eventId=" + event.getId();
        }
        System.out.println("asd\n");

        return "redirect:add-tag";
    }
}
