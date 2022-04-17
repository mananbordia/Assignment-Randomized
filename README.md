## Presentation

1. Install `tmux` on your server. 

MacOS
```
brew install tmux
```
Linux
```
sudo apt install tmux
```
Copy the minimal `~/.tmux.conf` file into your **home** directory.
```bash
set -g prefix C-a
setw -g pane-base-index 1
set -g mouse

bind r source-file ~/.tmux.conf \; display "Reloaded Configration, I guess"

bind C-a send-prefix
bind | split-window -h
bind - split-window -v

# Moving between panes
bind h select-pane -L
bind j select-pane -D
bind k select-pane -U
bind l select-pane -R

bind -r C-h select-window -t :-
bind -r C-l select-window -t :+

bind H resize-pane -L 5
bind J resize-pane -D 5
bind K resize-pane -U 5
bind L resize-pane -R 5
set -g default-terminal "xterm-256color"

###############
# pane border #
###############
#set -g pane-border-status bottom
##colors for pane borders
#setw -g pane-border-style fg=green,bg=black
#setw -g pane-active-border-style fg=colour118,bg=black
#setw -g automatic-rename off
#setw -g pane-border-format ' #{pane_index} #{pane_title} : #{pane_current_path} '
# active pane normal, other shaded out​
setw -g window-style fg=colour28,bg=colour16
setw -g window-active-style fg=colour46,bg=colour16
```
Or if you are in the repository directory
```bash
cp TMUX_CONF.conf ~/.tmux.conf
```
Now simply run `./tmuxScript` and voila!

Assignments are previewed in their respective numbered panes.
