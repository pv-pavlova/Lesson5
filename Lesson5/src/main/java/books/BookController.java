package books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/")
public class BookController {

    private final BookStorage bookStorage;

    @Autowired
    public BookController(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("ListB", bookStorage.index());
        return "index";
    }

    @GetMapping("/add")
    public String newBook(Model model){
        model.addAttribute("Book", new Book());
        return "add";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("Book", bookStorage.show(id));
        return "show";
    }

    @PostMapping()
    public String create(@ModelAttribute("Book") Book book ){
        bookStorage.add(book);
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookStorage.remove(id);
        return "redirect:/";
    }


}