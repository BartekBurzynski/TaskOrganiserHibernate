package pl.bb.taskorganiser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bb.taskorganiser.model.TaskDone;
import pl.bb.taskorganiser.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) TaskDone done) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Task> query;
        if (done == null) {
            query = entityManager.createQuery("SELECT t from Task t order by t.endTime", Task.class);
        } else {
            query = entityManager.createQuery("SELECT t from Task t  WHERE t.done = '" + done + "" +
                    "' order by t.endTime", Task.class);
        }


        List<Task> tasks = query.getResultList();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskToAdd", new Task());
        entityManager.close();

        return "home";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();

        return "redirect:/";
    }

    @PostMapping("/back")
    public String mainBack() {

        return "redirect:/";
    }
/*
    @GetMapping("/edit")
    public String taskToEdit(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Task> query = entityManager.createQuery("UPDATE Task t SET t.done='ZROBIONE' WHERE  t.id= '"
                + task.getId() + "'  ", Task.class);
        entityManager.persist(query);
        entityManager.getTransaction().commit();
        return "home";
    }
*/
}
