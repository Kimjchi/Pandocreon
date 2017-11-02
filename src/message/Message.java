package message; 

/**
 * Classe repr�sentant un message �mis par les classes �metrices (observables) 
 * du jeu afin de transmettre plus d'information � leurs observateurs.
 * Un message est compos� d'un type d�fini depuis une �num�ration, et d'un corps
 * quelconque. Le type concret du corps d�pend g�n�ralement du type et n'est pas 
 * impos� � la compilation. On veillera donc � utiliser sciemment ce membre afin
 * de ne pas provoquer d'erreur � l'ex�cution li�e � un probl�me de type.
 *
 */
public class Message {

	/**
	 * Repr�sente le type du message envoy�.
	 *
	 * Une valeur nulle est possible, mais devra �tre g�r�e en cons�quence par 
	 * le receveur du message.
	 * 
	 */
    private MessageType type;

	/**
	 * Repr�sente le corps, quelconque, du message.
	 *
	 * Une valeur nulle est possible, mais devra �tre g�r�e en
	 * cons�quence par le receveur du message.
	 */
    private Object body;

	/**
	 * Constructeur complet.
	 * 
	 * Cr�e un message � partir de son type et de son corps.
	 *
	 * @param type Le type du message �mis
	 * @param body Le corps, quelconque, du message �mis
	 */
    
    public Message(MessageType type, Object body) {
        this.type = type;
        this.body = body;
    }

	/**
	 * Constructeur raccourci.
	 * 
	 * Cr�e un message � partir de son type uniquement. Le corps du message est 
	 * optionnel et automatiquement rempli � null.
	 *
	 * @param type Le type du message �mis
	 * @see fr.bragabresolin.menhir.Core.Message.MessageType
	 */
	public Message(MessageType type) {
		this.type = type;
		this.body = null;
	}

	/**
	 * Retourne le type du message.
	 * 
	 * @return Le type du message
	 * @see fr.bragabresolin.menhir.Core.Message.MessageType
	 */
    public MessageType getType() {
        return type;
    }

	/**
	 * Retourne le corps du message.
	 * 
	 * @return Le corps du message
	 */
    public Object getBody() {
        return body;
    }
}