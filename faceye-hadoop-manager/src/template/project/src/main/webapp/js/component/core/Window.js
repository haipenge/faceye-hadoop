/**
 * Window,Write with bootstrap.
 */
var Modal = function(config) {$.extend(this,config);};
Modal.prototype = {
	id:'',
	header : true,
	footer:false,
	title : '',
	body:'',
	config : {},
	/**
	 * 配置
	 */
	config : function() {
		this.conf = {};
	},
	init : function(config) {
		$.extend(this.conf, config);
	},
	/**
	 * 显示window
	 */
	show : function() {
		$('body').append(this.build());
		$('#'+this.getId()).modal('show');
	},
	build : function() {
		var sb = new StringBuffer();
		sb.append('<div class="modal fade" id="'+this.getId()+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">');
		sb.append('<div class="modal-dialog">');
		sb.append('<div class="modal-content">');
		sb.append('<div class="modal-header">');
		sb.append('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>');
		sb.append('<h4 class="modal-title" id="myModalLabel">');
		sb.append(this.getTitle());
		sb.append('</h4>');
		sb.append('</div>');
		sb.append('<div class="modal-body">');
		sb.append(this.getBody());
		sb.append('</div>');
		if(this.getFooter()){
		sb.append('<div class="modal-footer">');
		sb.append('<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>');
		sb.append('<button type="button" class="btn btn-primary">保存</button>');
		sb.append('</div>');
		}
		sb.append('</div>');
		sb.append('</div>');
		sb.append('</div>');
		return sb.toString();
	},
	
	setHeader : function(header) {
		this.header = header;
	},
	getHeaer : function() {
		return this.header;
	},
	setTitle : function(title) {
		this.title = title;
	},
	getTitle : function() {
		if(this.title==='' && this.conf && this.conf.title){
			alert(this.conf.title);
			setTitle(config.title);
		}
		return this.title;
	},
	setBody:function(body){
		this.body=body;
	},
	getBody:function(){
		return this.body;
	},
	setId:function(id){
		this.id=id;
	},
	getId:function(){
		if(this.id===''||this.id ===undefined || this.id===null){
			this.id=Tools.id('win-');
		}
		return this.id;
	},
	getFooter:function(){
		return this.footer;
	},
	setFooter:function(footer){
		this.footer=footer;
	}
};
