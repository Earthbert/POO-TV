

# Homework POO-TV

### Daraban Albert-Timotei 324CA

## Implementation
I will go through all the class and present how they work, and interact with each-other.

* Database
  * is a Singleton that contains all the input received
    * to make it Singleton I made a private @JsonCreator constructor which save the instance
    * another advantage of using the above-mentioned annotation is not having the need for setters for field read from input 
* Movie contains
  * all basic information field about a movie and getters for them
  * equals method that return true if the movies have the same name
  * methods to modify some fields
* MovieList contains static methods that operate on Movie Lists
* User contains
  * all basic information field about a user and getters for them
  * actions a user can do (buy, watch, rate, like a movie, subscribe to a genre)
  * it also implements Observer, this make that a user notified when
    * a purchased movie is deleted
    * a movie is added of a genre the user is subscribed to
* UserAction contains static methods that operate on/with Database's User List.
* Action contains
  * all basic information field about an action and getters for them
* Filter contains
  * basic information about a filter action
  * apply method that filters a Movie List by calling Contains.apply and Sort.apply 
* Page contains
  * page type
  * current logged-in user
  * shown movies, to select from by "see details" action.
  * selected movie, if current page is "see details"
  * static Map that associates page types with a list of links (pages that you can go to) and a list of features
    * Pages and Features are identified by name String
  * methods that extract information from the map
* OutputWriter
  * writes output in an ArrayNode
  * contains 3 write method in depending on what it is written
* Execution contains
  * current page
  * start method that is called in main function to start doing received actions
  * ChangePageAction contains
    * methods for all "change page" type actions 
    * handle method that calls specific method in function of action name
  * OnPageAction contains
    * methods for all "on page" type actions
    * handle method that calls specific method in function of action name
  * DatabaseAction contains
    * methods for all "database" type actions
    * handle method that calls specific method in function of action name
  * The above-mentioned classes are used to better separate program logic
  * output writer
  * MementoCaretaker used to get back to previous pages
* memento package contains a simple implementation of Memento pattern
  * Memento is an Interface used to make sure an object can be saved/restored
  * MementoCaretaker offers an interface to save/restore different states of an object
  * LLMementoCaretaker is an implementation of MementoCaretaker using LinkedList
  * MementoCaretakerFactory offers an easy way to create a MementoCaretaker without having to know the implementation.
* observer package contains a simple implementation of Observer patter
  * I chose to make my own implementation because
    * the one from java.util is deprecated 
    * other implementations were too complicated for my use cases

Patterns used: Singleton, Observer, Memento, Factory.