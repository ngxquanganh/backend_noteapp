package anhnq.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "Notes")
public class Notes_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNotes;

    @Column(name = "idUser")
    private int idUser;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Content")
    private String Content;

    @Column(name = "Date_Create")
    private LocalDateTime DateCreate;

    @Column(name = "Date_Modify")
    private LocalDateTime DateModify;
}
