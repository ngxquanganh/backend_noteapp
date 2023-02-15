package anhnq.Controller;

import anhnq.Model.Notes_Model;
import anhnq.Repo.Note_Repo;
import anhnq.Response.MyResponse;

import anhnq.Utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class Notes_Controller {

    @Autowired
    Note_Repo NoteRepo;

    @PostMapping("/notes/create")
    public MyResponse create(
            @RequestParam("id_user") int id_user,
            @RequestParam("title") String Title,
            @RequestParam("content") String Content,
            @RequestParam("date_create") LocalDateTime date_create,
            @RequestParam("date_modify") LocalDateTime date_modify
    ) {
        System.out.println("id "+id_user + Title + Content + date_create + date_modify);
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            Notes_Model Note = Notes_Model.builder().Title(Title).Content(Content).DateCreate(date_create).DateModify(date_modify).idUser(id_user).build();
            session.persist(Note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MyResponse.success("ok");
    }

    @GetMapping("/notes/fetchallnotes")
    public MyResponse fetch(
            @RequestParam("id_user") int id_user
    ) {
        List<Notes_Model> listAllNotes;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "select n from Notes_Model n where n.idUser = " + id_user;
            listAllNotes = session.createQuery(hql, Notes_Model.class).list();
            session.getTransaction().commit();
        }
        if (listAllNotes.size() == 0) return MyResponse.fail("No note");
        return MyResponse.success(listAllNotes);
    }

    @GetMapping("notes/fetchallnotes2")
    public MyResponse fetch2(
            @RequestParam("id_user") Long id_user
    ) {
        List<Notes_Model> notes = NoteRepo.findByIdUser(id_user);
        if (notes.size() != 0) {
            return MyResponse.success(notes);
        } else {
            return MyResponse.fail("no");
        }
    }

    @PutMapping("/notes/updatenote")
    public MyResponse update(
            @RequestParam("id_note") int id_note,
            @RequestParam("content") String content,
            @RequestParam("date_modify") LocalDateTime date_modify
    ) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

//            Notes_Model n = (Notes_Model) session.get(Notes_Model.class,)

            String hql = "update Notes_Model n set n.Content = '" + content + "', n.DateModify = '" + date_modify + "' where n.idNotes = " + id_note;
            session.createQuery(hql, Notes_Model.class);

            session.getTransaction().commit();
            return MyResponse.success("Success");
        }
    }

    @PutMapping("/notes/updatenote2")
    public MyResponse update2
            (
                    @RequestParam("id_note") Long id_note,
                    @RequestParam("content") String content,
                    @RequestParam("date_modify") LocalDateTime date_modify
            )
    {
        Notes_Model note = NoteRepo.getReferenceById(id_note);
        note.setContent(content);
        note.setDateModify(date_modify);
        NoteRepo.save(note);
        return MyResponse.success("update success");
    }

    @DeleteMapping("/notes/deletenote")
    public MyResponse delete
            (
                    @RequestParam("id_note") Long id_note
            )
    {
        try {
            NoteRepo.deleteById(id_note);
            System.out.println("delete success " + id_note);
            return MyResponse.success("delete success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return MyResponse.fail("delete fail");
        }

    }
}
