@Controller
@RequestMapping("/person")
public class personController{
    @Autowired
	private PersonDao personDao;
	
	private List<Person> personList = new ArrayList<Person>();
	
	private String firstname;
	private String surname;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String gotoPersonAdmin() {
 		return "personAdmin";
 	}
	
	 /**
     * Adds a new Person
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("person") Person person) {
		logger.debug("Received request to add new credit card");
		
		// Delegate to service
		personDao.save(person);

		// Redirect to url
		return "redirect:/krams/main/record/list";
	}
}