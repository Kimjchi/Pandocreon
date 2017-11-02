package message; 

/**
 * Classe représentant un message émis par les classes émetrices (observables) 
 * du jeu afin de transmettre plus d'information à leurs observateurs.
 * Un message est composé d'un type défini depuis une énumération, et d'un corps
 * quelconque. Le type concret du corps dépend généralement du type et n'est pas 
 * imposé à la compilation. On veillera donc à utiliser sciemment ce membre afin
 * de ne pas provoquer d'erreur à l'exécution liée à un problème de type.
 *
 */
public class Message {

	/**
	 * Représente le type du message envoyé.
	 *
	 * Une valeur nulle est possible, mais devra être gérée en conséquence par 
	 * le receveur du message.
	 * 
	 */
    private MessageType type;

	/**
	 * Représente le corps, quelconque, du message.
	 *
	 * Une valeur nulle est possible, mais devra être gérée en
	 * conséquence par le receveur du message.
	 */
    private Object body;

	/**
	 * Constructeur complet.
	 * 
	 * Crée un message à partir de son type et de son corps.
	 *
	 * @param type Le type du message émis
	 * @param body Le corps, quelconque, du message émis
	 */
    
    public Message(MessageType type, Object body) {
        this.type = type;
        this.body = body;
    }

	/**
	 * Constructeur raccourci.
	 * 
	 * Crée un message à partir de son type uniquement. Le corps du message est 
	 * optionnel et automatiquement rempli à null.
	 *
	 * @param type Le type du message émis
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